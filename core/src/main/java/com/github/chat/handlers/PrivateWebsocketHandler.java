package com.github.chat.handlers;

import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketConnectionPool;
import com.github.chat.payload.Envelope;
import com.github.chat.payload.PrivateToken;
import com.github.chat.utils.JsonHelper;
import com.github.chat.utils.PrivateTokenProvider;
import com.github.chat.utils.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnMessage;
import javax.websocket.Session;

public class PrivateWebsocketHandler {

    private static final Logger log = LoggerFactory.getLogger(PrivateWebsocketHandler.class);

    private final Broker broker;

    private final WebsocketConnectionPool websocketConnectionPool;

    public PrivateWebsocketHandler(WebsocketConnectionPool websocketConnectionPool, Broker broker) {
        this.websocketConnectionPool = websocketConnectionPool;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload){
        try {
            Envelope env = JsonHelper.fromJson(payload, Envelope.class).get();
            PrivateToken token;
            String nickname;
            long roomId;
            switch(env.getTopic()) {
                case auth:
                    token = PrivateTokenProvider.decode(env.getPayload());
                    nickname = token.getNickname();
                    this.websocketConnectionPool.addSession(nickname,session);
                    broker.broadcast(websocketConnectionPool.getSessions(), env);
                    break;
                case sendTextMessage:
                    System.out.println(payload);
                    this.broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    break;
                case disconnect:
                    broker.broadcast(this.websocketConnectionPool.getSessions(), env);
                    token = PrivateTokenProvider.decode(env.getPayload());
                    nickname = token.getNickname();
                    websocketConnectionPool.removeSession(nickname);
                    websocketConnectionPool.getSession(nickname).close();
                    break;
                default:
            }
        } catch (Throwable e){
            //TODO single send to user about an error
            log.warn("Enter: {}", e.getMessage());
        }
    }
}