package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.Friend;
import com.gamehaven.steam.model.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {
}