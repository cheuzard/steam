package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class EsrbDTO {
    @JsonProperty("rating")
    private String rating;

    @JsonProperty("descriptors")
    private String descriptors;
}
