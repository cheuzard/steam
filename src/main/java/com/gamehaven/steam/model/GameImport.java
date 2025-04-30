package com.gamehaven.steam.model;

import jakarta.persistence.*;

@Entity
@Table(name = "game_imports")
public class GameImport {
    @Id
    @Column(name = "response_name")
    private String responseName;

    @Column(name = "release_date")
    private String releaseDate;

    @Column(name = "metacritic")
    private Integer metacritic;

    @Column(name = "recommendation_count")
    private Integer recommendationCount;

    @Column(name = "is_free")
    private Boolean isFree;

    @Column(name = "genre_is_non_game")
    private Boolean genreIsNonGame;

    @Column(name = "genre_is_indie")
    private Boolean genreIsIndie;

    @Column(name = "genre_is_action")
    private Boolean genreIsAction;

    @Column(name = "genre_is_adventure")
    private Boolean genreIsAdventure;

    @Column(name = "genre_is_casual")
    private Boolean genreIsCasual;

    @Column(name = "genre_is_strategy")
    private Boolean genreIsStrategy;

    @Column(name = "genre_is_rpg")
    private Boolean genreIsRPG;

    @Column(name = "genre_is_simulation")
    private Boolean genreIsSimulation;

    @Column(name = "genre_is_early_access")
    private Boolean genreIsEarlyAccess;

    @Column(name = "genre_is_free_to_play")
    private Boolean genreIsFreeToPlay;

    @Column(name = "genre_is_sports")
    private Boolean genreIsSports;

    @Column(name = "genre_is_racing")
    private Boolean genreIsRacing;

    @Column(name = "genre_is_massively_multiplayer")
    private Boolean genreIsMassivelyMultiplayer;

    @Column(name = "price_initial")
    private Integer priceInitial;

    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public Integer getRecommendationCount() {
        return recommendationCount;
    }

    public void setRecommendationCount(Integer recommendationCount) {
        this.recommendationCount = recommendationCount;
    }

    public Boolean getFree() {
        return isFree;
    }

    public void setFree(Boolean free) {
        isFree = free;
    }

    public Boolean getGenreIsNonGame() {
        return genreIsNonGame;
    }

    public void setGenreIsNonGame(Boolean genreIsNonGame) {
        this.genreIsNonGame = genreIsNonGame;
    }

    public Boolean getGenreIsIndie() {
        return genreIsIndie;
    }

    public void setGenreIsIndie(Boolean genreIsIndie) {
        this.genreIsIndie = genreIsIndie;
    }

    public Boolean getGenreIsAction() {
        return genreIsAction;
    }

    public void setGenreIsAction(Boolean genreIsAction) {
        this.genreIsAction = genreIsAction;
    }

    public Boolean getGenreIsAdventure() {
        return genreIsAdventure;
    }

    public void setGenreIsAdventure(Boolean genreIsAdventure) {
        this.genreIsAdventure = genreIsAdventure;
    }

    public Boolean getGenreIsCasual() {
        return genreIsCasual;
    }

    public void setGenreIsCasual(Boolean genreIsCasual) {
        this.genreIsCasual = genreIsCasual;
    }

    public Boolean getGenreIsStrategy() {
        return genreIsStrategy;
    }

    public void setGenreIsStrategy(Boolean genreIsStrategy) {
        this.genreIsStrategy = genreIsStrategy;
    }

    public Boolean getGenreIsRPG() {
        return genreIsRPG;
    }

    public void setGenreIsRPG(Boolean genreIsRPG) {
        this.genreIsRPG = genreIsRPG;
    }

    public Boolean getGenreIsSimulation() {
        return genreIsSimulation;
    }

    public void setGenreIsSimulation(Boolean genreIsSimulation) {
        this.genreIsSimulation = genreIsSimulation;
    }

    public Boolean getGenreIsEarlyAccess() {
        return genreIsEarlyAccess;
    }

    public void setGenreIsEarlyAccess(Boolean genreIsEarlyAccess) {
        this.genreIsEarlyAccess = genreIsEarlyAccess;
    }

    public Boolean getGenreIsFreeToPlay() {
        return genreIsFreeToPlay;
    }

    public void setGenreIsFreeToPlay(Boolean genreIsFreeToPlay) {
        this.genreIsFreeToPlay = genreIsFreeToPlay;
    }

    public Boolean getGenreIsSports() {
        return genreIsSports;
    }

    public void setGenreIsSports(Boolean genreIsSports) {
        this.genreIsSports = genreIsSports;
    }

    public Boolean getGenreIsRacing() {
        return genreIsRacing;
    }

    public void setGenreIsRacing(Boolean genreIsRacing) {
        this.genreIsRacing = genreIsRacing;
    }

    public Boolean getGenreIsMassivelyMultiplayer() {
        return genreIsMassivelyMultiplayer;
    }

    public void setGenreIsMassivelyMultiplayer(Boolean genreIsMassivelyMultiplayer) {
        this.genreIsMassivelyMultiplayer = genreIsMassivelyMultiplayer;
    }

    public Integer getPriceInitial() {
        return priceInitial;
    }

    public void setPriceInitial(Integer priceInitial) {
        this.priceInitial = priceInitial;
    }
}
