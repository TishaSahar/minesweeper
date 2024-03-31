package com.minesweeper.application.dto;

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
public class GameInfoResponse {
    private UUID game_id;
    private int width;
    private int height;
    private int mines_count;
    private String[][] field;
    private boolean completed;
}