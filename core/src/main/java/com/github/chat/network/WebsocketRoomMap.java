package com.github.chat.network;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WebsocketRoomMap {

    private Map<Integer, WebsocketConnectionPool> roomSession;

    public WebsocketRoomMap(Map<Integer, WebsocketConnectionPool> roomSession) {
        this.roomSession = roomSession;
    }

    public void addSession(Integer idRoom, Long userId, Session session) {
        if (Objects.isNull(this.roomSession)) {
            WebsocketConnectionPool ws = new WebsocketConnectionPool();
            ws.addSession(userId, session);
            this.roomSession.put(idRoom, ws);
        }
        if (this.roomSession.containsKey(idRoom)) {
            System.out.println("11");
            this.roomSession.get(idRoom).addSession(userId, session);
        } else {
            System.out.println("ss");
            WebsocketConnectionPool ws = new WebsocketConnectionPool();
            ws.addSession(userId, session);
            this.roomSession.put(idRoom, ws);
        }
    }

    public WebsocketConnectionPool getWSPool(Integer idRoom) {
        return this.roomSession.get(idRoom);
    }

    public List<Session> getSessions(Integer idRoom) {
        WebsocketConnectionPool room = this.roomSession.get(idRoom);
        return room.getSessions();
    }

    public void removeUserFromSession(Integer idRoom, Long userId) {
        this.roomSession.get(idRoom).removeSession(userId);
    }

    public void closeSession(Integer idRoom, Long UserId) {
        try {
            this.roomSession.get(idRoom).getSession(UserId).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
