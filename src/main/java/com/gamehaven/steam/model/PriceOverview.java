package com.gamehaven.steam.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceOverview {
    private String currency;
    private int initialPrice;
    private int finalPrice;
    private int discountPercent;
}
