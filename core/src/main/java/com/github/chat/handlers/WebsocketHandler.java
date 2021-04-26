package com.github.chat.handlers;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.http.WebSocket;

@ServerEndpoint(value = "/chat")
public class WebsocketHandler {

    @OnMessage
    public void messages(Session session, String payload){
        System.out.println(payload);
        //todo sending messages with websocket's
        try {
            session.getBasicRemote().sendText(payload);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
