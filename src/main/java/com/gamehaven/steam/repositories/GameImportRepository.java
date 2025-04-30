package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.ChatRoom;
import com.gamehaven.steam.model.GameImport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameImportRepository extends JpaRepository<GameImport, Integer> {

    GameImport findByResponseName(String responseName);
}