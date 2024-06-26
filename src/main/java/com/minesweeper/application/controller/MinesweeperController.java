package com.minesweeper.application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minesweeper.application.converter.GameConverter;
import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.dao.TurnDao;
import com.minesweeper.application.dto.GameInfoResponse;
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
    private final GameConverter gameConverter;

    @PostMapping("/new")
    public ResponseEntity<GameInfoResponse> newGameRequest(@RequestBody final GameDao aNewGame) {
        final Game newGame = gameProvider.createGame(aNewGame);
        log.info("New game created with id: {}", newGame.getGame_id());
        return ResponseEntity.ok().body(gameConverter.toGameInfoResponse(newGame));
    }

    @GetMapping("/turn")
    public ResponseEntity<GameInfoResponse> turnRequest(@RequestBody final TurnDao newTurn) {
        return ResponseEntity.ok().body(gameConverter.toGameInfoResponse(gameProvider.newTurn(newTurn)));
    }
}
