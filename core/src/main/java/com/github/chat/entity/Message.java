package com.github.chat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sc_history")
public class Message implements Serializable {

    private final static long serialVersionUID = -5210081818555834791L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "chat_id")
    private int chatId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "line_text")
    private String lineText;

    @Column(name = "created_at")
    private Date createdAd;

    public Message() {
    }

    public Message(int chatId, String nickname, String lineText, Date createdAd) {
        this.chatId = chatId;
        this.nickname = nickname;
        this.lineText = lineText;
        this.createdAd = createdAd;
    }

    public Message(long id, int chatId, String nickname, String lineText, Date createdAd) {
        this.id = id;
        this.chatId = chatId;
        this.nickname = nickname;
        this.lineText = lineText;
        this.createdAd = createdAd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getLineText() {
        return lineText;
    }

    public void setLineText(String lineText) {
        this.lineText = lineText;
    }

    public Date getCreatedAd() {
        return createdAd;
    }

    public void setCreatedAd(Date createdAd) {
        this.createdAd = createdAd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && chatId == message.chatId && nickname == message.nickname && Objects.equals(lineText, message.lineText) && Objects.equals(createdAd, message.createdAd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, nickname, lineText, createdAd);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", nickname=" + nickname +
                ", lineText='" + lineText + '\'' +
                ", createdAd=" + createdAd +
                '}';
    }
}
