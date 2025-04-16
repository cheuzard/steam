package com.gamehaven.model;

import java.time.LocalDate;

public class Game {
    private Long id;
    private String title;
    private String imageUrl;
    private LocalDate releaseDate;
    private Double price;
    private Double salePrice;
    private Integer discountPercentage;

    // Constructors
    public Game() {}

    public Game(Long id, String title, String imageUrl, LocalDate releaseDate, Double price) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public Game(Long id, String title, String imageUrl, LocalDate releaseDate, Double price, Double salePrice, Integer discountPercentage) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
        this.releaseDate = releaseDate;
        this.price = price;
        this.salePrice = salePrice;
        this.discountPercentage = discountPercentage;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public boolean isOnSale() {
        return salePrice != null && discountPercentage != null;
    }
}
