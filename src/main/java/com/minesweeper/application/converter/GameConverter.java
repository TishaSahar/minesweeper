package com.minesweeper.application.converter;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.minesweeper.application.common.exception.exception.InvalidParametersException;
import com.minesweeper.application.dto.GameInfoResponse;
import com.minesweeper.application.model.Game;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.FIELD)
public interface GameConverter {
    @Mapping(target = "field", source = "game.field", qualifiedByName = "toStringArrays")
    GameInfoResponse toGameInfoResponse(final Game game);

    @Named("toStringArrays")
    default String[][] toStringArrays(char[][] field) {
        String[][] result = new String[0][0];
        try {
            if (field != null) {
                result = new String[field.length][field[0].length];
                for (int i = 0; i < field.length; ++i) {
                    for (int j = 0; j < field[0].length; ++j) {
                        result[i][j] = String.valueOf(field[i][j]);
                    }
                }
            }
        } catch (NullPointerException e) {
            throw new InvalidParametersException();
        }
        return result;
    }
}
