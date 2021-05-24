package com.github.chat.payload;

import java.io.Serializable;
import java.util.Objects;

public class Token implements Serializable {

    long id;
    private String nickname;
    private long expire_in;
    private long createdAt;

    public Token() {
    }

    public Token(long id, String nickname, long expire_in, long createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.expire_in = expire_in;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", expire_in=" + expire_in +
                ", createdAt=" + createdAt +
                '}';
    }

    public Token(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.expire_in = System.currentTimeMillis() + 604800000;
        this.createdAt = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return id == token.id &&
                expire_in == token.expire_in &&
                createdAt == token.createdAt &&
                Objects.equals(nickname, token.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, expire_in, createdAt);
    }


}
