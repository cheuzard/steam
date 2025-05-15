package com.gamehaven.steam.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Platform {
    private boolean windows;
    private boolean mac;
    private boolean linux;
}
