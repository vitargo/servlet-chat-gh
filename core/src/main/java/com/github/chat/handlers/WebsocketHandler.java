package com.github.chat.handlers;

import com.github.chat.config.ControllerConfig;
import com.github.chat.controllers.MessagesController;
import com.github.chat.network.Broker;
import com.github.chat.network.WebsocketRoomMap;
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

    private final MessagesController messagesController = ControllerConfig.messagesController();

    private final Broker broker;

    private final WebsocketRoomMap websocketRoomMap;

    private Long idRoom = 1L;

    private String nickname = "";

    public WebsocketHandler(WebsocketRoomMap websocketRoomMap, Broker broker) {
        this.websocketRoomMap = websocketRoomMap;
        this.broker = broker;
    }

    @OnMessage
    public void messages(Session session, String payload){
        try {
            Envelope env = JsonHelper.fromJson(payload, Envelope.class).get();
            System.out.println(env);
            Token result;
            String message;
            switch(env.getTopic()) {
                case auth:
                    result = TokenProvider.decode(env.getPayload());
                    nickname = result.getNickname();
                    this.websocketRoomMap.addSession(idRoom,nickname,session);
                    broker.broadcast(websocketRoomMap.getSessions(idRoom), env);
                    break;
                case sendTextMessage:
                    message = env.getPayload();
                    messagesController.saveMessage(nickname, message);
                    this.broker.broadcast(this.websocketRoomMap.getSessions(idRoom),env);
                    break;
                case disconnect:
                    broker.broadcast(this.websocketRoomMap.getSessions(idRoom), env);
                    result = TokenProvider.decode(env.getPayload());
                    nickname = result.getNickname();
                    websocketRoomMap.removeUserFromSession(idRoom,nickname);
                    break;
                default:
            }
        } catch (Throwable e){
//            TODO single sand to user about an error
            log.warn("Enter: {}", e.getMessage());
        }
    }

    public WebsocketRoomMap getWebsocketRoomMap() {
        return websocketRoomMap;
    }
}
