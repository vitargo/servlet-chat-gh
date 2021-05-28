package com.github.chat.network;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebsocketRoomMap {

    private Map<Long, WebsocketConnectionPool> roomSession;

    private WebsocketConnectionPool websocketConnectionPool;

    public void addSession(Long idRoom, String nickname, Session session) {
        if (this.roomSession.containsValue(idRoom)) {
            this.roomSession.get(idRoom).addSession(nickname, session);
        } else {
            WebsocketConnectionPool ws = new WebsocketConnectionPool();
            ws.addSession(nickname,session);
            this.roomSession.put(idRoom,ws);
        }
    }

    public List<Session> getSessions(Long idRoom) {
        WebsocketConnectionPool room = this.roomSession.get(idRoom);
        return room.getSessions();
    }

    public void removeUserFromSession(Long idRoom, String nickname) {
        this.roomSession.get(idRoom).removeSession(nickname);
    }

    public void closeSession(Long idRoom, String nickname){
        try {
            this.roomSession.get(idRoom).getSession(nickname).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
