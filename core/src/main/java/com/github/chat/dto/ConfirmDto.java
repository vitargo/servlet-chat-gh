package com.github.chat.dto;

import java.util.Objects;

public class ConfirmDto {

    String nickName;

    String token;

    public ConfirmDto(String nickName, String token) {
        this.nickName = nickName;
        this.token = token;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmDto that = (ConfirmDto) o;
        return Objects.equals(nickName, that.nickName) && Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName, token);
    }

    @Override
    public String toString() {
        return "ConfirmDto{" +
                "nickName='" + nickName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
