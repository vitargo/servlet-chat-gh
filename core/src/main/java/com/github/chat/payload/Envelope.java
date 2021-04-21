package com.github.chat.payload;

import java.util.Objects;

public class Envelope {

    private Topic topic;

    private String payload;

    public Envelope() {
    }

    public Envelope(Topic topic, String payload) {
        this.topic = topic;
        this.payload = payload;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getPayload() {
        return payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Envelope envelope = (Envelope) o;
        return Objects.equals(topic, envelope.topic) && Objects.equals(payload, envelope.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, payload);
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "topic=" + topic +
                ", payload='" + payload + '\'' +
                '}';
    }
}
