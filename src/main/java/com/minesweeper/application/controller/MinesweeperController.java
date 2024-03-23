package com.minesweeper.application.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.model.Game;
import com.minesweeper.application.service.GameProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MinesweeperController {
    private final GameProvider gameProvider;

    @PostMapping("/new")
    public ResponseEntity<Game> newGameRequest(@RequestBody final GameDao aNewGame) {
        final Game newGame = gameProvider.createGame(aNewGame);
        log.info("New game created with id: {}", newGame.getGame_id());
        return ResponseEntity.ok().body(newGame);
    }
}
