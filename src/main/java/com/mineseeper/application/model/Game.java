package com.mineseeper.application.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Game {
    @Getter
    private final UUID id = UUID.randomUUID();
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int minesCount;
    
    @Getter
    @Setter
    private char[][] field;

    Game(final int aWidth, final int aHeight, final int aMinesCount) {
        width = aWidth;
        height = aHeight;
        minesCount = aMinesCount;
        for (int i = 0; i < aWidth; ++i) {
            for (int j = 0; j < aHeight; ++j) {
                field[i][j] = ' ';
            }
        }
    }
}
