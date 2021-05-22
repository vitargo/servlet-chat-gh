package com.github.chat.payload;

import java.io.Serializable;
import java.util.Objects;

public class Token implements Serializable {

    private String nickname;
    private long expire_in;
    private long createdAt;

    public Token() {
    }

    public Token(String nickname, long expire_in, long createdAt) {
        this.nickname = nickname;
        this.expire_in = expire_in;
        this.createdAt = createdAt;
    }

    public Token(String nickname) {
        this.nickname = nickname;
        this.expire_in = System.currentTimeMillis() + 604800000;
        this.createdAt = System.currentTimeMillis();
    }

    public String getNickname() {
        return nickname;
    }

    public long getExpire_in() {
        return expire_in;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return expire_in == token.expire_in &&
                createdAt == token.createdAt &&
                Objects.equals(nickname, token.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname, expire_in, createdAt);
    }

    @Override
    public String toString() {
        return "Token{" +
                "nickname='" + nickname + '\'' +
                ", expire_in=" + expire_in +
                ", createdAt=" + createdAt +
                '}';
    }

}
