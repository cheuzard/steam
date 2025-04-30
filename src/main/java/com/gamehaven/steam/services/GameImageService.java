package com.gamehaven.steam.services;

import com.gamehaven.steam.model.GameImage;
import com.gamehaven.steam.repositories.GameImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameImageService {
    private GameImageRepository gameImageRepository;
    @Autowired
    public void setGameImageRepository(GameImageRepository gameImageRepository) {
        this.gameImageRepository = gameImageRepository;
    }



public void addImagesBulk(List<GameImage> images) {
    gameImageRepository.saveAll(images);
}


}
