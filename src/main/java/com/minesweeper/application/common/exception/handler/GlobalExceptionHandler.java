package com.minesweeper.application.common.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.minesweeper.application.common.exception.dto.ApiErrorResponse;
import com.minesweeper.application.common.exception.exception.InvalidParametersException;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ApiErrorResponseCreator apiErrorResponseCreator;
    private final ErrorDebugMessageCreator errorDebugMessageCreator;
    
    @ExceptionHandler(InvalidParametersException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMethodArgumentNotValidException(final InvalidParametersException exception) {
        String message = "Произошла непредвиденная ошибка";

        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(message);
        log.error("Handle method argument not valid exception: failed: message: {}, debugMessage: {}.",
                message, errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleMethodArgumentNotValidException(final NullPointerException exception) {
        String message = "Произошла непредвиденная ошибка";

        ApiErrorResponse apiErrorResponse = apiErrorResponseCreator.buildResponse(message);
        log.error("Handle method argument not valid exception: failed: message: {}, debugMessage: {}.",
                message, errorDebugMessageCreator.buildErrorDebugMessage(exception));

        return apiErrorResponse;
    }
}

