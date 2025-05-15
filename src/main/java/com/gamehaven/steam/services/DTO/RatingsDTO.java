package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingsDTO {
    @JsonProperty("esrb")
    private EsrbDTO esrb;

    @JsonProperty("pegi")
    private PegiDTO pegi;

    @JsonProperty("usk")
    private UskDTO usk;
}
