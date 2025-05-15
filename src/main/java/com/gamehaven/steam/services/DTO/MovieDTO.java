package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDTO {
    @JsonProperty("id")
    private long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("webm")
    private Map<String, String> webm;

    @JsonProperty("mp4")
    private Map<String, String> mp4;
}
