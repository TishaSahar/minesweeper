package com.minesweeper.application.util;

import java.util.Arrays;
import java.util.Random;

import com.minesweeper.application.dao.GameDao;

import static com.minesweeper.application.constants.MinesweeperConstants.*;

/**
 * Realizes the algorhythms of the game.
 */
public class GameUtil {
    public static char[][] buildField(final GameDao aGameDao) {
        char[][] field = new char[aGameDao.getHeight()][aGameDao.getWidth()];
        for (char[] cs : field) {
            Arrays.fill(cs, CLOSED);
        }

        // push mines to the field at the random places
        Random rand = new Random();
        int minesCounter = aGameDao.getMinesCount();
        while (minesCounter != 0) {
            int row = rand.nextInt(aGameDao.getHeight());
            int col = rand.nextInt(aGameDao.getWidth());
            if (field[row][col] != MINE) {
                field[row][col] = MINE;
                --minesCounter;
            }
        }

        return field;
    }
}
