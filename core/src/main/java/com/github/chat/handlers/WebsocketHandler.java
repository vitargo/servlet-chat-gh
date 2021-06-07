package com.github.chat.handlers;

import com.github.chat.entity.User;
import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.network.WebsocketRoomMap;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Topic;
import com.github.chat.repository.impl.UserRepoImpl;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.util.List;

public class WebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(WebsocketHandler.class);

    private final Broker broker;

    private final WebsocketRoomMap websocketRoomMap;


    public WebsocketHandler(WebsocketRoomMap websocketRoomMap, Broker broker) {
        this.websocketRoomMap = websocketRoomMap;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload) {
        Envelope env = JsonHelper.fromJson(payload, Envelope.class).get();
        Claims result;
        long id = 0L;
        try {
            switch (env.getTopic()) {
                case auth:
                    log.info("auth: " + env);
                    System.out.println(env.getPayload());
                    result = TokenProvider.decode(env.getPayload());
                    id = Long.parseLong(result.getId());
                    this.websocketRoomMap.addSession(env.getRoomId(), id, session);
                    WebsocketConnectionPool websocketConnectionPool = this.websocketRoomMap.getWSPool(env.getRoomId());
                    broker.broadcast(websocketConnectionPool.getSessions(), env);
                    List<Long> allId = websocketConnectionPool.getAllUsersOnLine();
                    List<User> allOnLine = UserRepoImpl.findByIdList(allId);
                    String all = JsonHelper.toJson(allOnLine).get();
                    Envelope allUsers = new Envelope(Topic.allUsersOnLine, all);
                    broker.broadcast(websocketConnectionPool.getSessions(), allUsers);
                    break;
                case message:
                    log.info("message: " + env);
                    websocketConnectionPool = this.websocketRoomMap.getWSPool(env.getRoomId());
                    this.broker.broadcast(this.websocketRoomMap.getSessions(env.getRoomId()), env);
                    break;
                case disconnect:
                    websocketConnectionPool = this.websocketRoomMap.getWSPool(env.getRoomId());
                    broker.broadcast(websocketConnectionPool.getSessions(), env);
                    result = TokenProvider.decode(env.getPayload());
                    id = Long.parseLong(result.getId());
                    websocketConnectionPool.removeSession(id);
                    websocketConnectionPool.getSession(id).close();
                    break;
                default:
            }
        } catch (Throwable e) {
            log.warn("Enter: {}", e.getMessage());
            synchronized( websocketRoomMap ) {
                websocketRoomMap.removeUserFromSession(env.getRoomId(), id);
            }
        }
    }
}
