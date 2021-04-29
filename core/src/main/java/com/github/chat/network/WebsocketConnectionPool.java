package com.github.chat.network;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class WebsocketConnectionPool {

    private ConcurrentHashMap<Long, Session> sessions = new ConcurrentHashMap<>();

    public void addSession(Long id, Session session) {
        this.sessions.put(id, session);
    }

    public void remove(long id) {
        this.sessions.remove(id);
    }

    public List<Session> getSessions() {
        return new ArrayList<>(this.sessions.values());
    }
}
