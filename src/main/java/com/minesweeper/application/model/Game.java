package com.minesweeper.application.model;

import static com.minesweeper.application.constants.MinesweeperConstants.*;

import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.util.GameUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private final UUID game_id = UUID.randomUUID();

    @Column(name = "width", nullable = false)
    private int width;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "minesCount", nullable = false)
    private int minesCount;

    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @Column(name = "field", nullable = true)
    private char[][] field;

    public Game(final GameDao aGameDao) {
        if (aGameDao.getWidth() < 2 || aGameDao.getWidth() > 30
                || aGameDao.getHeight() < 2 || aGameDao.getHeight() > 30
                || aGameDao.getMinesCount() > aGameDao.getWidth() * aGameDao.getHeight() - 1) {
            throw new InvalidParametersException();
        }
        width = aGameDao.getWidth();
        height = aGameDao.getHeight();
        minesCount = aGameDao.getMinesCount();
        completed = false;
        field = GameUtil.buildField(aGameDao);
    }
}
