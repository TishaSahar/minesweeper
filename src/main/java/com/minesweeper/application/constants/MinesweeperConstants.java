package com.minesweeper.application.constants;

public final class MinesweeperConstants {
    private MinesweeperConstants() {}
    
    /**
     * CLOSED
     */
    public static final char CLOSED = ' ';

    /**
     * BOOM
     */
    public static final char BOOM = 'X';

    /**
     * MINE
     */
    public static final char MINE = 'M';

    /*
     * OPEN
     */
    public static final char[] OPEN = {'0', '1', '2', '3', '4', '5', '6', '7', '8'};

    /**
     * MAX_SIZE
     */
    public static final int MAX_SIZE = 30;

    /**
     * MIN_SIZE
     */
    public static final int MIN_SIZE = 2;
}
