package com.gamehaven.steam.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Entity
@Table(name = "chat_room_members")
public class ChatRoomMember {
    @EmbeddedId
    private ChatRoomMemberId id;

    @MapsId("roomId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private com.gamehaven.steam.model.ChatRoom room;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private com.gamehaven.steam.model.User user;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "joined_at")
    private Instant joinedAt;

    public ChatRoomMemberId getId() {
        return id;
    }

    public void setId(ChatRoomMemberId id) {
        this.id = id;
    }

    public com.gamehaven.steam.model.ChatRoom getRoom() {
        return room;
    }

    public void setRoom(com.gamehaven.steam.model.ChatRoom room) {
        this.room = room;
    }

    public com.gamehaven.steam.model.User getUser() {
        return user;
    }

    public void setUser(com.gamehaven.steam.model.User user) {
        this.user = user;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }

}