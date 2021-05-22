package com.github.chat.network;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketConnectionPool {

    private ConcurrentHashMap<String, Session> sessions = new ConcurrentHashMap<>();

    public void addSession(String nickname, Session session) {
        this.sessions.put(nickname, session);
    }

    public List<Session> getSessions() {
        return new ArrayList<>(this.sessions.values());
    }

    public void removeSession(String nickname) {
        this.sessions.remove(nickname);
    }

    public Session getSession(String nickname) {
        return this.sessions.get(nickname);
    }
}
