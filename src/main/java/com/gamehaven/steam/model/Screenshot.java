package com.gamehaven.steam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ValueGenerationType;

@Entity
@Table(name = "screenshots")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Screenshot {
    @EmbeddedId
    private ScreenShotID screenShotID;

    @ManyToOne
    @MapsId("gameId")
    private Game game;

    private String pathThumbnail;
    private String pathFull;
}
