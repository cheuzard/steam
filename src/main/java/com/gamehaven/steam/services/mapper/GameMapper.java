package com.gamehaven.steam.services.mapper;

import com.gamehaven.steam.model.*;
import com.gamehaven.steam.services.DTO.*;
import com.gamehaven.steam.services.SteamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public final class GameMapper {

    static SteamService steamService;

    @Autowired
    GameMapper(SteamService steamService) {
        GameMapper.steamService = steamService;
    }

    public static Game toEntity(Optional<GameDetailsDTO> opdto) {
        if (opdto.isEmpty()) return null;
        GameDetailsDTO dto = opdto.get();



        Game.GameBuilder builder = Game.builder()
                .type(dto.getType())
                .title(dto.getTitle())
                .isFree(dto.isFree())
                .aboutTheGame(dto.getAboutTheGame())
                .shortDescription(dto.getShortDescription())
                .supportedLanguages(dto.getSupportedLanguages())
                .capsuleImage(dto.getCapsuleImage())
                .pcRequirements(map(dto.getPcRequirements()))
                .priceOverview(map(dto.getPriceOverview()))
                .platform(map(dto.getPlatforms()))
                .releaseDate(map(dto.getReleaseDate()))
                .ratings(map(dto.getRatings()));

        if (dto.getDevelopers() != null) builder.developers(dto.getDevelopers());
        if (dto.getPublishers() != null) builder.publishers(dto.getPublishers());

        if (dto.getGenres() != null) {
            builder.genres(dto.getGenres().stream().map(GameMapper::map).collect(Collectors.toList()));
        }
        if (dto.getScreenshots() != null) {
            builder.screenshots(dto.getScreenshots().stream().map(GameMapper::map).collect(Collectors.toList()));
        }
        if (dto.getTrailers() != null) {
            builder.trailers(dto.getTrailers().stream().map(GameMapper::map).collect(Collectors.toList()));
        }

        if (dto.getDlcs() != null) {
            List<Game> dlcs = dto.getDlcs().stream()
                    .map(id -> steamService.getGameDetails(String.valueOf(id)))        // fetch GameDetailsDTO for each DLC id
                    .map(dlcDto -> toEntity(dlcDto))  // recursively map DLCs
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            builder.dlcs(dlcs);
        }

        return builder.build();
    }

    /* ------------------------- simple sub‑mapping helpers ------------------------- */

    private static PcRequirements map(PcRequirementsDTO dto) {
        if (dto == null) return null;
        return new PcRequirements(dto.getMinimum(), dto.getRecommended());
    }

    private static Platform map(PlatformDTO dto) {
        if (dto == null) return null;
        return new Platform(dto.isWindows(), dto.isMac(), dto.isLinux());
    }

    private static PriceOverview map(PriceOverviewDTO dto) {
        if (dto == null) return null;
        return new PriceOverview(dto.getCurrency(), dto.getInitialPrice(), dto.getFinalPrice(), dto.getDiscountPercent());
    }

    private static ReleaseDate map(ReleaseDateDTO dto) {
        if (dto == null) return null;
        return new ReleaseDate(dto.isComingSoon(), dto.getDate());
    }

    private static Genre map(GenreDTO dto) {
        if (dto == null) return null;
        return new Genre(dto.getId(), dto.getDescription());
    }

    private static Screenshot map(ScreenshotDTO dto) {
        if (dto == null) return null;
        return new Screenshot(dto.getId(), dto.getPathThumbnail(), dto.getPathFull());
    }

    private static Movie map(MovieDTO dto) {
        if (dto == null) return null;
        return Movie.builder()
                .id(dto.getId())
                .name(dto.getName())
                .thumbnail(dto.getThumbnail())
                .webm(dto.getWebm() != null ? dto.getWebm() : new HashMap<>())
                .mp4(dto.getMp4() != null ? dto.getMp4() : new HashMap<>())
                .build();
    }

    private static Ratings map(RatingsDTO dto) {
        if (dto == null) return null;
        String esrbRating = null;
        String esrbDescriptors = null;
        if (dto.getEsrb() != null) {
            esrbRating = dto.getEsrb().getRating();
            esrbDescriptors = dto.getEsrb().getDescriptors();
        }
        String pegiRating = dto.getPegi() != null ? dto.getPegi().getRating() : null;
        String uskRating = dto.getUsk() != null ? dto.getUsk().getRating() : null;
        return new Ratings(esrbRating, esrbDescriptors, pegiRating, uskRating);
    }
}
