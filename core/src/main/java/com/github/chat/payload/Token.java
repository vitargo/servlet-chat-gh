package com.github.chat.payload;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Token implements Serializable {

    private Long id;
    private String first_name;
    private String last_name;
    private Date expire_in;
    private Date createdAt;

    public Token() {
    }

    public Token(Long id, String first_name, String last_name, Date expire_in, Date createdAt) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.expire_in = expire_in;
        this.createdAt = createdAt;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return Objects.equals(id, token.id) && Objects.equals(first_name, token.first_name) && Objects.equals(last_name, token.last_name) && Objects.equals(expire_in, token.expire_in) && Objects.equals(createdAt, token.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, last_name, expire_in, createdAt);
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

    public Date getExpire_in() {
        return expire_in;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
