package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceOverviewDTO {
    @JsonProperty("currency")
    private String currency;

    @JsonProperty("initial")
    private int initialPrice;

    @JsonProperty("final")
    private int finalPrice;

    @JsonProperty("discount_percent")
    private int discountPercent;
}
