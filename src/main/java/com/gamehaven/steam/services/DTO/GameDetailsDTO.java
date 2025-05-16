package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDetailsDTO {


    @JsonProperty("steam_appid")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("dlc")
    private List<Long> dlcs;

    @JsonProperty("name")
    private String title;

    @JsonProperty("is_free")
    private boolean isFree;

    @JsonProperty("about_the_game")
    private String aboutTheGame;

    @JsonProperty("short_description")
    private String shortDescription;

    @JsonProperty("supported_languages")
    private String supportedLanguages;

    @JsonProperty("capsule_image")
    private String capsuleImage;

    @JsonProperty("pc_requirements")
    private PcRequirementsDTO pcRequirements;

    @JsonProperty("developers")
    private List<String> developers;

    @JsonProperty("publishers")
    private List<String> publishers;

    @JsonProperty("price_overview")
    private PriceOverviewDTO priceOverview;

    @JsonProperty("platforms")
    private PlatformDTO platforms;

    @JsonProperty("genres")
    private List<GenreDTO> genres;

    @JsonProperty("screenshots")
    private List<ScreenshotDTO> screenshots;

    @JsonProperty("movies")
    private List<MovieDTO> trailers;

    @JsonProperty("release_date")
    private ReleaseDateDTO releaseDate;

    @JsonProperty("ratings")
    private RatingsDTO ratings;
}
