package com.gamehaven.steam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_room_members_entity")
public class ChatRoomMembersEntity {
    @Column(name = "room_id", nullable = false)
    private long roomId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "joined_at")
    private Timestamp joinedAt;

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Timestamp joinedAt) {
        this.joinedAt = joinedAt;
    }

}