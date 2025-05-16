package com.gamehaven.steam.controller;

import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.model.GameRepository;
import com.gamehaven.steam.model.Movie;
import com.gamehaven.steam.services.DTO.GameDetailsDTO;
import com.gamehaven.steam.services.DTO.SteamAppListResponseDTO;
import com.gamehaven.steam.services.SteamService;
import com.gamehaven.steam.services.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/steam")
public class SteamController {

    private final SteamService steamService;
    private final GameMapper gameMapper;
    private final GameRepository gameRepository;

    @Autowired
    public SteamController(SteamService steamService, GameMapper gameMapper,
                           GameRepository gameRepository) {
        this.steamService = steamService;
        this.gameMapper = gameMapper;
        this.gameRepository = gameRepository;
    }

    /**
     * Endpoint to retrieve all app IDs
     */
    @GetMapping("/apps")
    public ResponseEntity<List<SteamAppListResponseDTO.AppDTO>> getAllApps() {
        return ResponseEntity.ok(steamService.getAllSteamApps());
    }
    @GetMapping("/getapp")
    public ResponseEntity<Map<String, Object>> getApp(@RequestParam String appid){
        Map<String, Object> response = new HashMap<>();
        response.put("appid", appid);
        try{
            Optional<GameDetailsDTO> gameDetails =
                    steamService.getGameDetails(appid);
            gameDetails.ifPresent(gameDetailsDTO -> response.put("gameDetails", gameDetailsDTO));
        }catch (Exception e){
            System.out.println("erro getting game details: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }
    @GetMapping("/printApp")
    public ResponseEntity<Map<String, Object>> printApp(@RequestParam String appid){
        Map<String, Object> response = new HashMap<>();
        response.put("appid", appid);
        try{
            Optional<GameDetailsDTO> gameDetails =
                    steamService.getGameDetails(appid);
//            gameDetails.ifPresent(gameDetailsDTO -> response.put("gameDetails", gameMapper.toEntity(gameDetailsDTO)));
            if (gameDetails.isPresent()){
                Game g = gameMapper.toEntity(gameDetails.get());
                System.out.println(g.getTitle());
                System.out.println(g.getGenres());
//                response.put("game", g );
            }
        }catch (Exception e){
            System.out.println("erro getting game details: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saveApp")
    public ResponseEntity<Map<String, Object>> saveApp(@RequestParam String appid){
        Map<String, Object> response = new HashMap<>();
        response.put("appid", appid);
        try{
            Optional<GameDetailsDTO> gameDetails =
                    steamService.getGameDetails(appid);
            if(gameDetails.isPresent()) {
                System.out.println(gameDetails.get().getTrailers().get(0).getId());
                Game game = gameMapper.toEntity(gameDetails.get());
                System.out.println(game.toString());
                for (Movie m : game.getTrailers()){
                    System.out.println("gameId"+m.getId().getGameId());
                    System.out.println("Screenshot Id"+m.getId().getId());
                    System.out.println("mp4"+m.getMp4()+"\n");
                }
                    gameRepository.save(game);
                response.put("gameSaved", true);
            };
        }catch (Exception e){
            response.put("gameSaved", false);
            System.out.println("erro getting game details: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to test retrieving details for the first N games
     */
    @GetMapping("/test-first-games")
    public ResponseEntity<Map<String, Object>> getFirstNGamesDetails(
            @RequestParam(defaultValue = "200") int count) {

        // Get all Steam apps
        List<SteamAppListResponseDTO.AppDTO> allApps = steamService.getAllSteamApps();

        // Take the first N apps or fewer if there aren't enough
        List<SteamAppListResponseDTO.AppDTO> firstNApps = allApps.stream()
                .limit(count)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("totalAppsAvailable", allApps.size());
        response.put("requestedCount", count);
        response.put("actualCount", firstNApps.size());

        // Store successful and failed retrievals
        List<GameDetailsDTO> successfulRetrievals = new ArrayList<>();
        List<Long> failedRetrievals = new ArrayList<>();

        // For each app, try to get its details
        for (SteamAppListResponseDTO.AppDTO app : firstNApps) {
            try{
                Optional<GameDetailsDTO> gameDetails =
                        steamService.getGameDetails(String.valueOf(app.getAppid()));

                if (gameDetails.isPresent()) {
                    successfulRetrievals.add(gameDetails.get());
                } else {
                    failedRetrievals.add(app.getAppid());
                }
            }catch(HttpClientErrorException.TooManyRequests e){
                response.put("rateLimited", true);
                break;
            }
        }

        response.put("successfulRetrievals", successfulRetrievals);
        response.put("successCount", successfulRetrievals.size());
        response.put("failedRetrievals", failedRetrievals);
        response.put("failureCount", failedRetrievals.size());
        response.put("requestsBeforeRateLimit",successfulRetrievals.size() + failedRetrievals.size() );

        return ResponseEntity.ok(response);
    }

    /**
     * Alternative endpoint that processes games in batches to avoid overwhelming the Steam API
     */
    @GetMapping("/test-batch-games")
    public ResponseEntity<Map<String, Object>> getBatchGamesDetails(
            @RequestParam(defaultValue = "200") int count,
            @RequestParam(defaultValue = "20") int batchSize) {

        // Get all Steam apps
        List<SteamAppListResponseDTO.AppDTO> allApps = steamService.getAllSteamApps();
        System.out.println(allApps.size() + " apps retrieved");
        // Take the first N apps or fewer if there aren't enough
        List<SteamAppListResponseDTO.AppDTO> selectedApps = allApps.stream()
                .limit(count)
                .collect(Collectors.toList());

        Map<String, Object> response = new HashMap<>();
        response.put("totalAppsAvailable", allApps.size());
        response.put("requestedCount", count);
        response.put("actualCount", selectedApps.size());
        response.put("batchSize", batchSize);

        // Store successful and failed retrievals
        List<GameDetailsDTO> successfulRetrievals = new ArrayList<>();
        List<Long> failedRetrievals = new ArrayList<>();

        // Process in batches
        for (int i = 0; i < selectedApps.size(); i += batchSize) {
            int endIndex = Math.min(i + batchSize, selectedApps.size());
            List<SteamAppListResponseDTO.AppDTO> batch = selectedApps.subList(i, endIndex);

            // Process each app in the batch
            for (SteamAppListResponseDTO.AppDTO app : batch) {
                Optional<GameDetailsDTO> gameDetails =
                        steamService.getGameDetails(String.valueOf(app.getAppid()));

                if (gameDetails.isPresent()) {
                    successfulRetrievals.add(gameDetails.get());
                } else {
                    failedRetrievals.add(app.getAppid());
                }
            }

            // Add a small delay between batches to avoid rate limiting
            // but only if there are more batches to process
            if (endIndex < selectedApps.size()) {
                try {
                    Thread.sleep(1000); // 1 second delay between batches
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        response.put("successfulRetrievals", successfulRetrievals);
        response.put("successCount", successfulRetrievals.size());
        response.put("failedRetrievals", failedRetrievals);
        response.put("failureCount", failedRetrievals.size());

        return ResponseEntity.ok(response);
    }
}