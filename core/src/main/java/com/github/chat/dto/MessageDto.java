package com.github.chat.dto;

import java.util.Date;
import java.util.Objects;

public class MessageDto {

    private Integer roomId;

    private String text;

    private String nickname;

    private Date date;

    public MessageDto(Integer roomId, String text, String nickname, Date date) {
        this.roomId = roomId;
        this.text = text;
        this.nickname = nickname;
        this.date = date;
    }

    public MessageDto() {
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "roomId=" + roomId +
                ", text='" + text + '\'' +
                ", nickname='" + nickname + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(text, that.text) && Objects.equals(nickname, that.nickname) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, text, nickname, date);
    }
}
