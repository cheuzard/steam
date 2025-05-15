package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleaseDateDTO {
    @JsonProperty("coming_soon")
    private boolean comingSoon;

    @JsonProperty("date")
    private String date;
}
