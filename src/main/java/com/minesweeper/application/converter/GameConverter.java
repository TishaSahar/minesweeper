package com.minesweeper.application.converter;

import static com.minesweeper.application.constants.MinesweeperConstants.CLOSED;
import static com.minesweeper.application.constants.MinesweeperConstants.MINE;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.dto.GameInfoResponse;
import com.minesweeper.application.model.Game;
import com.minesweeper.application.util.GameUtil;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.FIELD)
public interface GameConverter {
    @Mapping(target = "field", source = "game.field", qualifiedByName = "toStringArrays")
    GameInfoResponse toGameInfoResponse(final Game game);

    @Named("toStringArrays")
    default String[][] toStringArrays(char[][] field) {
        Boolean isWin = GameUtil.isWinner(field);
        String[][] result = new String[0][0];
        try {
            if (field != null) {
                result = new String[field.length][field[0].length];
                for (int i = 0; i < field.length; ++i) {
                    for (int j = 0; j < field[0].length; ++j) {
                        if (MINE == field[i][j] && !isWin) {
                            result[i][j] = String.valueOf(CLOSED);
                        } else {
                            result[i][j] = String.valueOf(field[i][j]);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidParametersException();
        }
        return result;
    }
}
