package com.gamehaven.entity;


public class ChatMessages {

  private long id;
  private long roomId;
  private long senderId;
  private String message;
  private java.sql.Timestamp sentAt;


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


  public java.sql.Timestamp getSentAt() {
    return sentAt;
  }

  public void setSentAt(java.sql.Timestamp sentAt) {
    this.sentAt = sentAt;
  }

}
