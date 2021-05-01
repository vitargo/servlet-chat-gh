package com.github.chat.payload;

import java.io.Serializable;
import java.util.Objects;

public class Token implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private long expire_in;
    private long createdAt;

    public Token() {
    }

    public Token(Long id, String first_name, String last_name, long expire_in, long createdAt) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.expire_in = expire_in;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
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
        return expire_in == token.expire_in && createdAt == token.createdAt && Objects.equals(id, token.id) && Objects.equals(first_name, token.first_name) && Objects.equals(last_name, token.last_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, expire_in, createdAt);
    }

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", expire_in=" + expire_in +
                ", createdAt=" + createdAt +
                '}';
    }

   // {"id":1,"first_name":"Aaaa","last_name":"Bbbb","expire_in":1619646590519,"createdAt":1619646590519}
    //{"topic":"message","payload":"{"id":1,"first_name":"Aaaa","last_name":"Bbbb","expire_in":1619646590519,"createdAt":1619646590519}"}
    //{"topic":"auth","payload":"{"id":1,"first_name":"Aaaa","last_name":"Bbbb","expire_in":1619646590519,"createdAt":1619646590519}"}
//{"topic":"auth","payload":"{"id":1,"first_name":"Aaaa","last_name":"Bbbb","expire_in":1619646590519,"createdAt":1619646590519}"}
}
