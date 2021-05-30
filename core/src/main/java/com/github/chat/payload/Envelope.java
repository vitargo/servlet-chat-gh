package com.github.chat.payload;

import java.util.Objects;

public class Envelope {

    private Topic topic;

    private String nickName;

    private String payload;

    public Envelope() {
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "topic=" + topic +
                ", nickName='" + nickName + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }

    public Envelope(Topic topic, String nickName, String payload) {
        this.topic = topic;
        this.nickName = nickName;
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPayload() {
        return payload;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return topic == envelope.topic && Objects.equals(nickName, envelope.nickName) && Objects.equals(payload, envelope.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, nickName, payload);
    }


}
