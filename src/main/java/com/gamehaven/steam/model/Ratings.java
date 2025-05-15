package com.gamehaven.steam.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ratings {
    private String esrbRating;
    private String esrbDescriptors;
    private String pegiRating;
    private String uskRating;
}
