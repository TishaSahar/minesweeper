package com.minesweeper.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
import org.springframework.stereotype.Service;

import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.converter.GameConverter;
import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.model.Game;
import com.minesweeper.application.repository.GamesRepository;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameProvider {
    private final GameConverter gameConverter;
    private final GamesRepository gameRepository;
    
    public Game getGameById(final UUID gameId) {
        return getGameOptionalById(gameId).orElseGet(null);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    private Optional<Game> getGameOptionalById(final UUID gameId) {
        return gameRepository.findById(gameId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Game createGame(final GameDao gameDao) throws InvalidParametersException {
        try {
            final Game game = new Game(gameDao);
            return gameRepository.save(game);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
