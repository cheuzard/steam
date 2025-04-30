package com.gamehaven.steam.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private com.gamehaven.steam.model.ChatRoom room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private com.gamehaven.steam.model.User sender;

    @Lob

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @ColumnDefault("CURRENT_TIMESTAMP(6)")
    @Column(name = "sent_at")
    private Instant sentAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public com.gamehaven.steam.model.ChatRoom getRoom() {
        return room;
    }

    public void setRoom(com.gamehaven.steam.model.ChatRoom room) {
        this.room = room;
    }

    public com.gamehaven.steam.model.User getSender() {
        return sender;
    }

    public void setSender(com.gamehaven.steam.model.User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sentAt) {
        this.sentAt = sentAt;
    }

}