package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenreDTO {
    @JsonProperty("id")
    private String id;

    @JsonProperty("description")
    private String description;
}
