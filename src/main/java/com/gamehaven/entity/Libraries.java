package com.gamehaven.entity;


public class Libraries {

  private long userId;
  private long gameId;
  private java.sql.Timestamp addedAt;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getGameId() {
    return gameId;
  }

  public void setGameId(long gameId) {
    this.gameId = gameId;
  }


  public java.sql.Timestamp getAddedAt() {
    return addedAt;
  }

  public void setAddedAt(java.sql.Timestamp addedAt) {
    this.addedAt = addedAt;
  }

}
