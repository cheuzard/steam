package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.ChatRoomMember;
import com.gamehaven.steam.model.ChatRoomMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRoomMemberRepository extends JpaRepository<ChatRoomMember, ChatRoomMemberId> {
  }