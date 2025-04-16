package com.gamehaven.model;

public class FeaturedGame extends Game {
    private String description;

    public FeaturedGame() {
        super();
    }

    public FeaturedGame(Long id, String title, String imageUrl, String description) {
        super(id, title, imageUrl, null, null);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
