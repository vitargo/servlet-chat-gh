package com.github.chat.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sc_rooms")
public class Room {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private long id;

    @Column(name = "name_room")
    private String roomName;

    @Column(name = "admin")
    private long adminId;

    public Room() {
    }

    public Room(String roomName, long adminId) {
        this.roomName = roomName;
        this.adminId = adminId;
    }

    public Room(long id, String roomName, long adminId) {
        this.id = id;
        this.roomName = roomName;
        this.adminId = adminId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", adminId=" + adminId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id && adminId == room.adminId && Objects.equals(roomName, room.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, adminId);
    }
}
