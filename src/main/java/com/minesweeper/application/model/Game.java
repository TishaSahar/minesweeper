package com.minesweeper.application.model;

import java.util.UUID;

import com.minesweeper.application.dao.GameDao;

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
    
    @Column(name = "field", nullable = true)
    private String field;

    public Game(final GameDao aGameDao) {
        width = aGameDao.getWidth();
        height = aGameDao.getHeight();
        minesCount = aGameDao.getMinesCount();
        for (int i = 0; i < height * width; ++i) {
            field += ' ';
        }
    }
}
