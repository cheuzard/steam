package com.gamehaven.entity;


public class Games {

  private long id;
  private String title;
  private String description;
  private String developer;
  private java.sql.Date releaseDate;
  private double price;
  private String coverUrl;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }


  public java.sql.Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(java.sql.Date releaseDate) {
    this.releaseDate = releaseDate;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getCoverUrl() {
    return coverUrl;
  }

  public void setCoverUrl(String coverUrl) {
    this.coverUrl = coverUrl;
  }

}
