package com.gamehaven.steam.model;

import com.gamehaven.steam.model.*;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "games")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Game {
    @Id
    private Long id;

    @ManyToMany
    @JoinTable(name = "game_dlcs",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "dlc_id")
    )
    @ToString.Exclude
    private List<Game> dlcs = new ArrayList<>();

    private String type;

    private String title;

    private boolean isFree;

    @Column(columnDefinition = "TEXT")
    private String aboutTheGame;

    @Column(columnDefinition = "TEXT")
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String supportedLanguages;

    private String capsuleImage;

    @Embedded
    private PcRequirements pcRequirements;

    @ElementCollection
    @CollectionTable(name = "game_developers", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "developer")
    private List<String> developers = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "game_publishers", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "publisher")
    private List<String> publishers = new ArrayList<>();

    @Embedded
    private PriceOverview priceOverview;

    @Embedded
    private Platform platform;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "game_genres",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    @ToString.Exclude
    private List<Genre> genres = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Screenshot> screenshots = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "game_id")
    @ToString.Exclude
    private List<Movie> trailers = new ArrayList<>();

    @Embedded
    private ReleaseDate releaseDate;


    @Embedded
    private Ratings ratings;
}

