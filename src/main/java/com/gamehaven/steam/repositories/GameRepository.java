package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Game getGameByTitle(String title);
    List<Game> findAllByOrderByMetacriticDesc();
    List<Game> findAllByOrderByReleaseDateDesc();
    Page<Game> findAll(Pageable pageable);
}