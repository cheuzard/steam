package com.gamehaven.steam.services;

import com.gamehaven.steam.exceptions.*;
import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private final GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public void addGamesBulk(List<Game> games) {
        gameRepository.saveAll(games);
    }
    public void addGame(Game game) {
        gameRepository.save(game);
    }
    public boolean isGameExists(String gameName) {
        return gameRepository.getGameByTitle(gameName) != null;
    }
    public List<Game> getRandomGames(int count) {
        List<Game> allGames = gameRepository.findAll(); // Load all games
        Collections.shuffle(allGames); // Shuffle them randomly
        if (count > allGames.size()) {
            count = allGames.size(); // Prevent out-of-bound error
        }
        return allGames.subList(0, count); // Take only the number you want
    }
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public List<Game> getBestGames(int count) {
        List<Game> games = gameRepository.findAllByOrderByMetacriticDesc();
        games = games.subList(0, count *3);
        Collections.shuffle(games);
        return games.subList(0, count);
    }
    public Game getFeaturedGame(String title) {
        return gameRepository.getGameByTitle(title);
    }
    public List<Game> getBestOfRecent(int count) {
        Pageable top25 = PageRequest.of(0, count * 4, Sort.by(Sort.Direction.DESC, "releaseDate"));
        List<Game> recentGames = gameRepository.findAll(top25).getContent();

        // Sort by Metacritic descending
        recentGames = recentGames.stream()
                .sorted(Comparator.comparing(Game::getMetacritic, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList()).subList(0, count*2);

        // Shuffle in place
        Collections.shuffle(recentGames);

        return recentGames.stream().limit(count).toList();
    }


}
