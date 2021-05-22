package com.github.chat.handlers;

import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.Token;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;

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
            String nickname;
            switch(env.getTopic()) {
                case auth:
                    System.out.println(env.getPayload());
                    result = TokenProvider.decode(env.getPayload());
                    nickname = result.getNickname();
                    this.websocketConnectionPool.addSession(nickname,session);
                    broker.broadcast(websocketConnectionPool.getSessions(), env);
                    break;
                case sendTextMessage:
                    System.out.println(payload);
                    this.broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    break;
                case disconnect:
                    broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    result = TokenProvider.decode(env.getPayload());
                    nickname = result.getNickname();
                    websocketConnectionPool.removeSession(nickname);
                    websocketConnectionPool.getSession(nickname).close();
                    break;
                default:
            }
        } catch (Throwable e){
            //TODO single sand to user about an error
            log.warn("Enter: {}", e.getMessage());
        }
    }
}
