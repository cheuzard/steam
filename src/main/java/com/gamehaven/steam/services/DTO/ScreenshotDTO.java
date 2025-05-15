package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScreenshotDTO {
    @JsonProperty("id")
    private long id;

    @JsonProperty("path_thumbnail")
    private String pathThumbnail;

    @JsonProperty("path_full")
    private String pathFull;
}
