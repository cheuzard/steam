package com.gamehaven.entity;


public class ChatRooms {

  private long id;
  private String name;
  private long isGroup;
  private java.sql.Timestamp createdAt;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getIsGroup() {
    return isGroup;
  }

  public void setIsGroup(long isGroup) {
    this.isGroup = isGroup;
  }


  public java.sql.Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(java.sql.Timestamp createdAt) {
    this.createdAt = createdAt;
  }

}
