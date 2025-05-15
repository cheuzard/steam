package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlatformDTO {
    @JsonProperty("windows")
    private boolean windows;

    @JsonProperty("mac")
    private boolean mac;

    @JsonProperty("linux")
    private boolean linux;
}
