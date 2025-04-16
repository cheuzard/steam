package com.gamehaven.steam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StoreController {

    @GetMapping("/")
    public String home(Model model) {
        // Featured game
        FeaturedGame featuredGame = new FeaturedGame(
                1L,
                "goat simulator",
                "https://cheuzard.com/recources/images/cyberpunk2077.jpg",
                "An open-world, action-adventure story set in Night City..."
        );
        model.addAttribute("featuredGame", featuredGame);

        // New releases
        List<Game> newReleases = new ArrayList<>();
        newReleases.add(new Game(2L, "Dark Souls III", "https://cheuzard.com/recources/images/darksouls3.jpg", 
                LocalDate.of(2024, 4, 21), 59.99));
        newReleases.add(new Game(3L, "Horizon Forbidden West", "https://cheuzard.com/recources/images/horizonforbiddenwest.jpg", 
                LocalDate.of(2024, 4, 10), 69.99));
        newReleases.add(new Game(4L, "Animal Well", "https://cheuzard.com/recources/images/animalwell.jpg", 
                LocalDate.of(2024, 4, 10), 29.99));
        model.addAttribute("newReleases", newReleases);

        // Trending games
        List<Game> trendingGames = new ArrayList<>();
        trendingGames.add(new Game(5L, "Elden Ring", "https://cheuzard.com/recources/images/eldenring.jpg", 
                null, 52.99));
        trendingGames.add(new Game(6L, "Red Dead Redemption II", "https://cheuzard.com/recources/images/reddeadredemption2.jpg", 
                null, 28.99));
        trendingGames.add(new Game(7L, "Stardew Valley", "https://cheuzard.com/recources/images/stardewvalley.jpg", 
                null, 14.99));
        model.addAttribute("trendingGames", trendingGames);

        // Games on sale
        List<Game> gamesOnSale = new ArrayList<>();
        gamesOnSale.add(new Game(8L, "The Witcher 3", "https://cheuzard.com/recources/images/witcher3.jpg", 
                null, 31.99, 11.99, 70));
        gamesOnSale.add(new Game(9L, "Star Wars Jedi: Fallen Order", "https://cheuzard.com/recources/images/jedifallenorder.jpg", 
                null, 17.99, 7.99, 80));
        gamesOnSale.add(new Game(10L, "Hades", "https://cheuzard.com/recources/images/hades.jpg", 
                null, 12.49, 12.49, 50));
        model.addAttribute("gamesOnSale", gamesOnSale);

        return "index";
    }
}
