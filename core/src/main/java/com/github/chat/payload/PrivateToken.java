package com.github.chat.payload;

import java.util.Objects;

public class PrivateToken {

    private String nickname;
    private long expire_in;
    private long createdAt;
    private long room_Id;

    public PrivateToken(String nickname, long expire_in, long createdAt, long room_Id) {
        this.nickname = nickname;
        this.expire_in = expire_in;
        this.createdAt = createdAt;
        this.room_Id = room_Id;
    }

    public PrivateToken() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public long getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(long expire_in) {
        this.expire_in = expire_in;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getRoom_Id() {
        return room_Id;
    }

    public void setRoom_Id(long room_Id) {
        this.room_Id = room_Id;
    }

    @Override
    public String toString() {
        return "PrivateToken{" +
                "nickname='" + nickname + '\'' +
                ", expire_in=" + expire_in +
                ", createdAt=" + createdAt +
                ", room_Id=" + room_Id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateToken that = (PrivateToken) o;
        return expire_in == that.expire_in && createdAt == that.createdAt && room_Id == that.room_Id && Objects.equals(nickname, that.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, expire_in, createdAt, room_Id);
    }
}
