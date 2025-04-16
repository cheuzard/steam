package com.gamehaven.entity;


public class Friends {

  private long userId;
  private long friendId;
  private String status;
  private java.sql.Timestamp requestedAt;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getFriendId() {
    return friendId;
  }

  public void setFriendId(long friendId) {
    this.friendId = friendId;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getRequestedAt() {
    return requestedAt;
  }

  public void setRequestedAt(java.sql.Timestamp requestedAt) {
    this.requestedAt = requestedAt;
  }

}
