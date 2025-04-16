package com.gamehaven.entity;


public class Sales {

  private long id;
  private long userId;
  private long gameId;
  private java.sql.Timestamp purchaseDate;
  private double priceAtPurchase;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


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


  public java.sql.Timestamp getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(java.sql.Timestamp purchaseDate) {
    this.purchaseDate = purchaseDate;
  }


  public double getPriceAtPurchase() {
    return priceAtPurchase;
  }

  public void setPriceAtPurchase(double priceAtPurchase) {
    this.priceAtPurchase = priceAtPurchase;
  }

}
