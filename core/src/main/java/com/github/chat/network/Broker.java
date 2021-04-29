package com.github.chat.network;

import com.github.chat.payload.Envelope;
import com.github.chat.utils.JsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.List;

public class Broker {

    private static final Logger log = LoggerFactory.getLogger(Broker.class);

    public void broadcast(List<Session> sessions, Envelope payload) {
        String str = JsonHelper.toJson(payload).orElseThrow();
        sessions.forEach(session ->{
            try {
                session.getBasicRemote().sendText(str);
            } catch (IOException e) {
                log.warn("Enter: {}", e.getMessage());
            }
        });
    }

    public void send(Session session, Envelope payload){

    }
}
