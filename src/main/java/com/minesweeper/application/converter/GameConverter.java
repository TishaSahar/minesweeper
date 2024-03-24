package com.minesweeper.application.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.InjectionStrategy;

import com.minesweeper.application.dao.GameDao;
import com.minesweeper.application.model.Game;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface GameConverter {
    GameDao toGameDao(final Game game);
}
