package com.gamehaven.steam.controller;


import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.model.User;
import com.gamehaven.steam.services.GameService;
import com.gamehaven.steam.services.UserService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

//@RestController
//@RequestMapping("/")
@Controller
public class SiteController {
    private final GameService gameService;
    @Autowired
    public SiteController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Game> games = gameService.getBestOfRecent(3+1);
        model.addAttribute("games",gameService.getBestGames(4*2));
//        model.addAttribute("game",gameService.getFeaturedGame("Borderlands 2"));
        model.addAttribute("game",games.get(0));
        model.addAttribute("RGames", games.subList(1, games.size()));
        return "index";
    }
}
