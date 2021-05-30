package com.github.chat.payload;

import java.util.Objects;

public class Envelope {

    private Topic topic;

    private String nickName;

    private Integer roomId;

    private String payload;

    public Envelope() {
    }

    public Envelope(Topic topic, String nickName, Integer roomId, String payload) {
        this.topic = topic;
        this.nickName = nickName;
        this.roomId = roomId;
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return topic == envelope.topic && Objects.equals(roomId, envelope.roomId) && Objects.equals(payload, envelope.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, roomId, payload);
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "topic=" + topic +
                ", roomId=" + roomId +
                ", payload='" + payload + '\'' +
                '}';
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
