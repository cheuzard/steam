package com.gamehaven.steam.services.GameImport;

import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GameUpdater {
    RawGImport rawGImport;
    SteamGridImport steamGridImport;
    GameService gameService;

    @Autowired
    public GameUpdater(SteamGridImport steamGridImport, RawGImport rawGImport, GameService gameService) {
        this.steamGridImport = steamGridImport;
        this.rawGImport = rawGImport;
        this.gameService = gameService;
    }

    /* ============ public entry point ============ */
    public void updateGame(Game g) {
        System.out.println();
        System.out.print("//////");
        System.out.print(g.getTitle());
        System.out.println("\\\\\\\\\\\\");
        try{
//        rawGImport.getGameDetails(g);
//        System.out.println("gotten details for : "+ g.getTitle());
//        steamGridImport.getGameGrid(g);
//        System.out.println("grid for "+ g.getTitle() + " : "+g.getBannerUrl());
        rawGImport.addGameImages(g);
        System.out.println("screenshots for "+ g.getTitle());
        }catch(Exception e){
            System.out.println("error while updating game details: " + e.getMessage());
        }
        gameService.addGame(g);
    }
    public void importGames() {
        List<Game> games= gameService.getAllGames();
        for (Game g : games) {
            updateGame(g);
            System.out.println("finished updating game: "+ g.getTitle());
        }
    }

}
