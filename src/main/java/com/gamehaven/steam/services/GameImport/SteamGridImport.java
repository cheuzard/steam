package com.gamehaven.steam.services.GameImport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.gamehaven.steam.exceptions.ExternalApiException;
import com.gamehaven.steam.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class SteamGridImport {
    private static final String BASE_STEAMGRID = "https://www.steamgriddb.com/api/v2";
    private static final String STEAM_ID_URL   = BASE_STEAMGRID + "/search/autocomplete/{gameName}";
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;
    private final HttpEntity<String> requestEntity;
    private final HttpHeaders steamGridHeader = new HttpHeaders();


    @Autowired
    public SteamGridImport(RestTemplate restTemplate,
                           @Value("${api.steamGrid}") String steamGridKey,
                           ObjectMapper objectMapper) {

        this.restTemplate  = restTemplate;
        this.objectMapper  = objectMapper;

        steamGridHeader.set("Authorization", "Bearer " + steamGridKey);
        this.requestEntity = new HttpEntity<>(steamGridHeader);
    }






    private Long getSteamId(String gameName) {
        ResponseEntity<String> rsp = restTemplate.exchange(
                STEAM_ID_URL, HttpMethod.GET, requestEntity, String.class, gameName);

        JsonNode root = ApiUtil.parseBody(rsp, "SteamGrid-id lookup");
        return root.path("data").get(0).path("id").asLong();
    }

     void getGameGrid(Game game) {
        Long id = getSteamId(game.getTitle());

        String gridUrl  = BASE_STEAMGRID + "/grids/game/{id}";

        /* banner 920×430 */
        ResponseEntity<String> rsp = restTemplate.exchange(
                UriComponentsBuilder
                        .fromUriString(gridUrl)
                        .queryParam("dimensions", "920x430")
                        .buildAndExpand(id)
                        .toUri(),
                HttpMethod.GET, requestEntity, String.class);

        JsonNode root = ApiUtil.parseBody(rsp, "SteamGrid-banner");
        try{
            game.setBannerUrl(root.path("data").get(0).path("url").asText());
        }catch(NullPointerException e){
            game.setBannerUrl("");
        }

        /* cover 600×900 */
        rsp  = restTemplate.exchange(
                UriComponentsBuilder
                        .fromUriString(gridUrl)
                        .queryParam("dimensions", "600x900")
                        .buildAndExpand(id)
                        .toUri(),
                HttpMethod.GET, requestEntity, String.class);

        root = ApiUtil.parseBody(rsp, "SteamGrid-cover");

        try{
            game.setCoverUrl(root.path("data").get(0).path("url").asText());
        }catch(NullPointerException e){
            game.setCoverUrl("");
        }

    }
}
