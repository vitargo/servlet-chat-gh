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

    public void addSession(Integer idRoom, String nickname, Session session) {
        if (Objects.isNull(this.roomSession)) {
            WebsocketConnectionPool ws = new WebsocketConnectionPool();
            ws.addSession(nickname, session);
            this.roomSession.put(idRoom,ws);
        }
        if (this.roomSession.containsKey(idRoom)) {
            System.out.println("11");
            this.roomSession.get(idRoom).addSession(nickname, session);
        } else {
            System.out.println("ss");
            WebsocketConnectionPool ws = new WebsocketConnectionPool();
            ws.addSession(nickname, session);
            this.roomSession.put(idRoom, ws);
        }
    }

    public List<Session> getSessions(Integer idRoom) {
        WebsocketConnectionPool room = this.roomSession.get(idRoom);
        return room.getSessions();
    }

    public void removeUserFromSession(Integer idRoom, String nickname) {
        this.roomSession.get(idRoom).removeSession(nickname);
    }

    public void closeSession(Integer idRoom, String nickname) {
        try {
            this.roomSession.get(idRoom).getSession(nickname).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
