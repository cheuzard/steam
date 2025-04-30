package com.gamehaven.steam.services.GameImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamehaven.steam.exceptions.ExternalApiException;
import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.model.GameImage;
import com.gamehaven.steam.services.GameImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.gamehaven.steam.exceptions.NoScreenshotsFound;

@Service
public class RawGImport {
    private final RestTemplate restTemplate;
    private final HttpEntity<String> requestEntity;
    private final HttpHeaders steamGridHeader = new HttpHeaders();

    private final GameImageService gameImageService;
    private final String rawGKey;
    private static final String BASE_RAWG      = "https://api.rawg.io/api/games";
    private final ObjectMapper objectMapper;

    @Autowired
    public RawGImport(RestTemplate restTemplate, GameImageService gameImageService,
                      @Value("${api.rawg}")      String rawGKey,
                      ObjectMapper objectMapper) {

        this.restTemplate  = restTemplate;
        this.gameImageService = gameImageService;
        this.rawGKey       = rawGKey;
        this.objectMapper  = objectMapper;
        this.requestEntity = new HttpEntity<>(steamGridHeader);

    }

    private Long getRawGId(String gameName) {
        ResponseEntity<String> rsp = restTemplate.exchange(
                UriComponentsBuilder.fromUriString(BASE_RAWG)
                        .queryParam("search", gameName)
                        .queryParam("key", rawGKey)
                        .build().toUriString(),
                HttpMethod.GET, HttpEntity.EMPTY, String.class);

        JsonNode root = ApiUtil.parseBody(rsp, "RAWG-Id-search");
        return root.path("results").get(0).path("id").asLong();
    }

     void getGameDetails(Game g) {
        Long id = getRawGId(g.getTitle());

        ResponseEntity<RawgGameDTO> rspDetails = restTemplate.exchange(
                UriComponentsBuilder.fromUriString(BASE_RAWG + "/{rawGId}")
                        .queryParam("key", rawGKey)
                        .buildAndExpand(id).toUri(),
                HttpMethod.GET, HttpEntity.EMPTY, RawgGameDTO.class);

        RawgGameDTO dto;
        try {
            // tiny detour to avoid unchecked warning
            String json = objectMapper.writeValueAsString(rspDetails.getBody());
            dto = objectMapper.readValue(json, RawgGameDTO.class);
        } catch (JsonProcessingException e) {
            throw new ExternalApiException("RAWG payload malformed", e);
        }


//        g.setTitle(dto.name);
        g.setDescription(dto.description);
        g.setReleaseDate(LocalDate.parse(dto.released));

        if (dto.developers != null && !dto.developers.isEmpty()) {
            g.setDeveloper(dto.developers.get(0).name);
        }
    }

     void addGameImages(Game g) {
        Integer id =  g.getId();

        ResponseEntity<String> rspImages = restTemplate.exchange(
                UriComponentsBuilder.fromUriString(BASE_RAWG + "/{rawGId}" + "/screenshots")
                        .queryParam("key",rawGKey)
                        .buildAndExpand(id).toUri(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                String.class
        );
        try{
            JsonNode results = ApiUtil.parseBody(rspImages,"RawG-Images-Search").path("results");
            GameImage image;
            List<GameImage> images = new ArrayList<>();
            for (int i = 0; i < results.size(); i++) {
                image = new GameImage();
                image.setGame(g);
                image.setImageUrl(results.get(i).path("image").asText());
                images.add(image);
                System.out.println(image.getImageUrl());
            }
            if (images.isEmpty()) {
                throw new NoScreenshotsFound("No screenshots found for game " + g.getTitle());
            }
            gameImageService.addImagesBulk(images);
        }catch (ExternalApiException e){
            throw new ExternalApiException("RAWG payload malformed", e);
        }
    }
}
