package com.minesweeper.application.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Getter
    @Id
    private final UUID id = UUID.randomUUID();
    @Getter
    private int width;
    @Getter
    private int height;
    @Getter
    private int minesCount;
    
    @Getter
    @Setter
    private String field;

    Game(final int aWidth, final int aHeight, final int aMinesCount) {
        width = aWidth;
        height = aHeight;
        minesCount = aMinesCount;
        for (int i = 0; i < aWidth * aHeight; ++i) {
            field += ' ';
        }
    }
}
