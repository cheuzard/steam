package com.gamehaven.steam.services;

import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.model.GameImport;
import com.gamehaven.steam.repositories.GameImportRepository;
import com.gamehaven.steam.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GameImportService {
    GameImportRepository gameImportRepository;
    @Autowired
    public void GameImportService(GameImportRepository gameImportRepository) {
        this.gameImportRepository = gameImportRepository;
    }
    public List<GameImport> getGameImports() {
        return gameImportRepository.findAll();
    }
    public void deleteGameImport(GameImport gameImport) {
        gameImportRepository.delete(gameImport);
    }
    public GameImport getGameByName(final String name) {
        return gameImportRepository.findByResponseName(name);
    }
}
