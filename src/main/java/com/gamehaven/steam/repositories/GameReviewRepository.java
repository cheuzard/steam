package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameReviewRepository extends JpaRepository<GameReview, Integer> {
}