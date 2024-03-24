package com.minesweeper.application.common.exception.exception;

public class InvalidParametersException extends RuntimeException {
    public InvalidParametersException() {
        super("Произошла непредвиденная ошибка");
    }
}
