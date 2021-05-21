package com.github.chat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "sc_history")
public class History implements Serializable {

    private final static long serialVersionUID = -5210081818555834791L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "chat_id")
    private int chatId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "line_text")
    private String lineText;

    @Column(name = "created_at")
    private Date createdAd;

    public History() {
    }

    public History(long id, int chatId, long userId, String lineText, Date createdAd) {
        this.id = id;
        this.chatId = chatId;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
        History history = (History) o;
        return id == history.id && chatId == history.chatId && userId == history.userId && Objects.equals(lineText, history.lineText) && Objects.equals(createdAd, history.createdAd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, userId, lineText, createdAd);
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", userId=" + userId +
                ", lineText='" + lineText + '\'' +
                ", createdAd=" + createdAd +
                '}';
    }
}
