package com.minesweeper.application.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/app")
public class MinesweeperController {
    private static final UUID gameId = UUID.randomUUID();

    @PostMapping("/new")
    public ResponseEntity<String> newGame(@RequestBody final String newGame) {
        log.info("New game created with id: {}", gameId.toString());
        return ResponseEntity.ok().body(newGame);
    }
}
