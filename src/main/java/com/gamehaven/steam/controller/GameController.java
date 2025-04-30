package com.gamehaven.steam.controller;

import com.gamehaven.steam.services.GameImport.GameUpdater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Contrôleur REST chargé de l’ajout d’un jeu par son nom.
 */
@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameUpdater gameImport;

    @Autowired
    public GameController(GameUpdater gameImport) {
        this.gameImport = gameImport;
    }


    @PostMapping
    public ResponseEntity<Void> createGame() {
        // La logique de création (appel à RAWG + SteamGridDB) est
        // entièrement encapsulée dans le service.
        gameImport.importGames();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}