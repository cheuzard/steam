package com.gamehaven.steam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_messages_entity")
public class ChatMessagesEntity {
    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "room_id", nullable = false)
    private long roomId;

    @Column(name = "sender_id", nullable = false)
    private long senderId;

    @Column(name = "message")
    private String message;

    @Column(name = "sent_at")
    private Timestamp sentAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }

}