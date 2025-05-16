package com.gamehaven.steam.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @EmbeddedId
    private MovieID id;

    @ManyToOne
    @MapsId("gameId")
    private Game game;

    private String name;
    private String thumbnail;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_webm", joinColumns = @JoinColumn(name = "movie_id"))
    @MapKeyColumn(name = "quality")
    @Column(name = "url")
    private Map<String, String> webm = new HashMap<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "movie_mp4", joinColumns = @JoinColumn(name = "movie_id"))
    @MapKeyColumn(name = "quality")
    @Column(name = "url")
    private Map<String, String> mp4 = new HashMap<>();
}
