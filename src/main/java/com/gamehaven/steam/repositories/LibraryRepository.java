package com.gamehaven.steam.repositories;

import com.gamehaven.steam.model.Library;
import com.gamehaven.steam.model.LibraryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library, LibraryId> {
}