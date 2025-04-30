package com.gamehaven.steam.model;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
enum status {
    pending,
    accepted,
    blocked
    }
@Entity
@Table(name = "friends")
public class Friend {
    @EmbeddedId
    private FriendId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private com.gamehaven.steam.model.User user;

    @MapsId("friendId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "friend_id", nullable = false)
    private com.gamehaven.steam.model.User friend;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'pending'")
    @Column(name = "status")
    private status status;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "requested_at")
    private Instant requestedAt;

    public FriendId getId() {
        return id;
    }

    public void setId(FriendId id) {
        this.id = id;
    }

    public com.gamehaven.steam.model.User getUser() {
        return user;
    }

    public void setUser(com.gamehaven.steam.model.User user) {
        this.user = user;
    }

    public com.gamehaven.steam.model.User getFriend() {
        return friend;
    }

    public void setFriend(com.gamehaven.steam.model.User friend) {
        this.friend = friend;
    }

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(status status) {
        this.status = status;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }

}