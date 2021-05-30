package com.github.chat.handlers;

import com.github.chat.entity.User;
import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Token;
import com.github.chat.payload.Topic;
import com.github.chat.repository.impl.UserRepoImpl;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.util.List;

public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final Broker broker;

    private final WebsocketConnectionPool websocketConnectionPool;

    public WebsocketHandler(WebsocketConnectionPool websocketConnectionPool, Broker broker) {
        this.websocketConnectionPool = websocketConnectionPool;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload){
        try {
            Envelope env = JsonHelper.fromJson(payload, Envelope.class).get();
            Token result;
            long id;
            switch(env.getTopic()) {
                case auth:
                    System.out.println(env.getPayload());
                    result = TokenProvider.decode(env.getPayload());
                    id = result.getId();
                    this.websocketConnectionPool.addSession(id,session);
                    broker.broadcast(websocketConnectionPool.getSessions(), env);
                    List<Long> allId = websocketConnectionPool.getAllUsersOnLine();
                    List<User> allOnLine = UserRepoImpl.findByIdList(allId);
                    String all = JsonHelper.toJson(allOnLine).get();
                    Envelope allUsers = new Envelope(Topic.allUsersOnLine, all);
                    broker.broadcast(websocketConnectionPool.getSessions(), allUsers);
                    break;
                case message:
                    this.broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    break;
                case disconnect:
                    broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    result = TokenProvider.decode(env.getPayload());
                    id = result.getId();
                    websocketConnectionPool.removeSession(id);
                    websocketConnectionPool.getSession(id).close();
                    break;
                default:
            }
        } catch (Throwable e){
            log.warn("Enter: {}", e.getMessage());
        }
    }
}
