package com.gamehaven.steam.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamehaven.steam.services.DTO.GameDetailsDTO;
import com.gamehaven.steam.services.DTO.GameResponseDTO;
import com.gamehaven.steam.services.DTO.SteamAppListResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SteamService {

    private static final String STEAM_API_BASE_URL = "https://store.steampowered.com/api/appdetails";
    private static final String STEAM_APP_LIST_URL = "http://api.steampowered.com/ISteamApps/GetAppList/v0001/";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public SteamService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Retrieves the list of all Steam apps with their IDs and names
     *
     * @return List of app DTOs from the Steam API
     */
    public List<SteamAppListResponseDTO.AppDTO> getAllSteamApps() {
        try {
            ResponseEntity<SteamAppListResponseDTO> response = restTemplate.getForEntity(
                    STEAM_APP_LIST_URL,
                    SteamAppListResponseDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                SteamAppListResponseDTO appListResponse = response.getBody();

                if (appListResponse.getApplist() != null &&
                        appListResponse.getApplist().getApps() != null &&
                        appListResponse.getApplist().getApps().getAppList() != null) {

                    return appListResponse.getApplist().getApps().getAppList();
                }
            }

            return new ArrayList<>();
        } catch (Exception e) {
            System.err.println("Error retrieving Steam app list: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Retrieves detailed game information from Steam API by app ID
     *
     * @param appId The Steam app ID of the game
     * @return Optional containing game details if found, empty otherwise
     */
    public Optional<GameDetailsDTO> getGameDetails(String appId) {

            // Build the request URL with the app ID
            String requestUrl = UriComponentsBuilder.fromHttpUrl(STEAM_API_BASE_URL)
                    .queryParam("appids", appId)
                    .build()
                    .toUriString();

            // Make API call to Steam

       ResponseEntity<String> response = restTemplate.getForEntity(requestUrl, String.class);
        try {
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                // Parse the JSON response
                Map<String, GameResponseDTO> responseMap = objectMapper.readValue(
                        response.getBody(),
                        objectMapper.getTypeFactory().constructMapType(
                                Map.class,
                                String.class,
                                GameResponseDTO.class
                        )
                );

                // Check if response contains data for the requested app ID
                if (responseMap.containsKey(appId)) {
                    GameResponseDTO gameResponse = responseMap.get(appId);

                    // Check if the request was successful and has data
                    if (gameResponse.isSuccess() && gameResponse.getData() != null) {
                        return Optional.of(gameResponse.getData());
                    }
                }
            }

            return Optional.empty();
        } catch (Exception e) {
            // Log the exception
            System.err.println("Error retrieving game details for appId " + appId + ": " + e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }


}