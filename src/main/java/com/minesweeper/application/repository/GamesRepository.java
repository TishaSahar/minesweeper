package com.minesweeper.application.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minesweeper.application.model.Game;

@Repository
public interface GamesRepository extends JpaRepository<Game, UUID> {
    Optional<Game> findById(UUID id);
}
