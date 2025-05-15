package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

// Main wrapper class for the entire response
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamGameResponseDTO {
    // The JSON has an ID (e.g. "205635") as the key to the response object
    private Map<String, GameResponseDTO> games;

    // Helper method to get the first game in the response
    public GameResponseDTO getFirstGame() {
        if (games == null || games.isEmpty()) {
            return null;
        }
        return games.values().iterator().next();
    }
}
