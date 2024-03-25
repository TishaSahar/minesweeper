package com.minesweeper.application.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.dao.TurnDao;
import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.model.Game;
import com.minesweeper.application.repository.GamesRepository;
import com.minesweeper.application.util.GameUtil;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameProvider {
    private final GamesRepository gameRepository;

    public Game getGameById(final UUID gameId) {
        return getGameOptionalById(gameId).orElseThrow(() -> new InvalidParametersException());
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = true)
    private Optional<Game> getGameOptionalById(final UUID gameId) {
        return gameRepository.findById(gameId);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Game createGame(final GameDao gameDao) throws InvalidParametersException {
        Game game = new Game();
        try {
            game = new Game(gameDao);
            game = gameRepository.save(game);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw e;
        }
        return game;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Game newTurn(final TurnDao turnDao) throws InvalidParametersException {
        Game currentGame = new Game();
        try {
            currentGame = getGameById(turnDao.getGame_id());
            GameUtil.makeTurn(currentGame, turnDao);
            currentGame = gameRepository.save(currentGame);
        } catch (InvalidParametersException e) {
            log.error("Cannot process current turn {}", e.getMessage());
            throw e;
        }
        return currentGame;
    }
}
