package com.gamehaven.entity;


public class ChatRoomMembers {

  private long roomId;
  private long userId;
  private java.sql.Timestamp joinedAt;


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


  public java.sql.Timestamp getJoinedAt() {
    return joinedAt;
  }

  public void setJoinedAt(java.sql.Timestamp joinedAt) {
    this.joinedAt = joinedAt;
  }

}
