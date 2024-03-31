package com.minesweeper.application.util;

import java.util.Arrays;
import java.util.Random;

import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.dao.TurnDao;
import com.minesweeper.application.model.Game;

import lombok.extern.slf4j.Slf4j;

import static com.minesweeper.application.constants.MinesweeperConstants.*;

/**
 * Realizes the algorhythms of the game.
 */
@Slf4j
public class GameUtil {
    public static char[][] buildField(final GameDao aGameDao) {
        char[][] field = new char[aGameDao.getHeight()][aGameDao.getWidth()];
        for (char[] cs : field) {
            Arrays.fill(cs, CLOSED);
        }

        // push mines to the field at the random places
        Random rand = new Random();
        int minesCounter = aGameDao.getMines_count();
        while (minesCounter != 0) {
            int row = rand.nextInt(aGameDao.getHeight());
            int col = rand.nextInt(aGameDao.getWidth());
            if (field[row][col] != MINE) {
                field[row][col] = MINE;
                --minesCounter;
            }
        }
        printField(field);

        return field;
    }

    private static void printField(char[][] field) {
        System.out.println("\n\t|FIELD|");
        for (int row = 0; row < field.length; ++row) {
            System.out.print("\n|");
            for (int col = 0; col < field[0].length; ++col) {
                System.out.print(String.valueOf(field[row][col]) + "|");
            }
        }
        System.out.println("\n");
    }

    private static int countMinesNear(char[][] field, int row, int col) {
        int mines = 0;
        mines += checkIsMine(field, row + 1, col + 1);
        mines += checkIsMine(field, row, col + 1);
        mines += checkIsMine(field, row - 1, col + 1);
        mines += checkIsMine(field, row - 1, col);
        mines += checkIsMine(field, row - 1, col - 1);
        mines += checkIsMine(field, row, col - 1);
        mines += checkIsMine(field, row + 1, col - 1);
        mines += checkIsMine(field, row + 1, col);
        return mines;
    }

    private static int checkIsMine(char[][] field, int row, int col) {
        if (row < 0 || row >= field.length || col < 0 || col >= field[0].length) {
            return 0;
        }
        if(field[row][col] == MINE) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void makeTurn(Game aGame, final TurnDao aTurnDao) {
        char[][] field = aGame.getField();
        char cell = field[aTurnDao.getRow()][aTurnDao.getCol()];
        if (cell == MINE) {
            log.info("Game {} over :(", aGame.getGame_id());
            gameOver(aGame);
            aGame.setCompleted(true);
            return;
        }
        if (isOpen(cell)) {
            log.error("Incorrect turn coordinates!");
            throw new InvalidParametersException();
        }
        if (cell == CLOSED) {
            aGame.setField(openEmptyCells(field, aTurnDao.getRow(), aTurnDao.getCol()));
        }
        if (isWinner(field)) {
            aGame.setField(win(field));
            aGame.setCompleted(true);
        }
        printField(field);
    }

    public static Boolean isOpen(char cell) {
        for (char c : OPEN) {
            if (cell == c) {
                return true;
            }
        }
        return false;
    }

    public static char[][] openEmptyCells(char[][] field, int row, int col) {
        if (row < 0 || row >= field.length || col < 0 || col >= field[0].length) {
            return field;
        }
        field[row][col] = OPEN[countMinesNear(field, row, col)];
        if (countMinesNear(field, row, col) == 0) {
            openEmptyCells(field, row + 1, col + 1);
            openEmptyCells(field, row, col + 1);
            openEmptyCells(field, row - 1, col + 1);
            openEmptyCells(field, row - 1, col);
            openEmptyCells(field, row - 1, col - 1);
            openEmptyCells(field, row, col - 1);
            openEmptyCells(field, row + 1, col - 1);
            openEmptyCells(field, row + 1, col);
        }
        return field;   
    }

    public static boolean isWinner(char[][] field) {
        for (int row = 0; row < field.length; ++row) {
            for (int col = 0; col < field[0].length; ++col) {
                if (!isOpen(field[row][col])) {
                    if (MINE != field[row][col]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static char[][] win(char[][] field) {
        for (int row = 0; row < field.length; ++row) {
            for (int col = 0; col < field[0].length; ++col) {
                if (isOpen(field[row][col])) {
                    continue;
                }
                if (field[row][col] == MINE) {
                    field[row][col] = MINE;
                } else {
                    openEmptyCells(field, row, col);
                }
            }
        }
        return field;
    }

    private static void gameOver(Game aGame) {
        char[][] field = aGame.getField();
        for (int row = 0; row < field.length; ++row) {
            for (int col = 0; col < field[0].length; ++col) {
                if (isOpen(field[row][col])) {
                    continue;
                }
                if (field[row][col] == MINE) {
                    field[row][col] = BOOM;
                } else {
                    openEmptyCells(field, row, col);
                }
            }
        }
        aGame.setField(field);
    }

    public static void hideTheMines(char[][] field) {
        for (int row = 0; row < field.length; ++row) {
            for (int col = 0; col < field[0].length; ++col) {
                if (MINE == field[row][col]) {
                    field[row][col] = CLOSED;
                }
            }
        }
    }
}
