package com.gamehaven.steam.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PcRequirements {
    @Column(length = 4000)
    private String minimum;

    @Column(length = 4000)
    private String recommended;
}
