package com.github.chat.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class RoomRegDto {

    private String nameRoom;

    private List<String> users;

    private Long adminId;


    public RoomRegDto(String nameRoom, Long adminId) {
        this.nameRoom = nameRoom;
        this.adminId = adminId;
    }

    @JsonCreator
    public RoomRegDto(@JsonProperty("nameRoom") String nameRoom,
                      @JsonProperty("users") List<String> users) {
        this.nameRoom = nameRoom;
        this.users = users;
        this.adminId = 0L;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public RoomRegDto() {
    }

    @Override
    public String toString() {
        return "RoomRegDto{" +
                "nameRoom='" + nameRoom + '\'' +
                ", users=" + users +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomRegDto that = (RoomRegDto) o;
        return Objects.equals(nameRoom, that.nameRoom) && Objects.equals(users, that.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameRoom, users);
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
