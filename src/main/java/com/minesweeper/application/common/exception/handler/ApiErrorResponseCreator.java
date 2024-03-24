package com.minesweeper.application.common.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.minesweeper.application.common.exception.dto.ApiErrorResponse;

@Service
public class ApiErrorResponseCreator {

    public ApiErrorResponse buildResponse(String errorMessage) {
        return ApiErrorResponse.builder().error(errorMessage).build();
    }

    public ApiErrorResponse buildResponse(Exception exception, HttpStatus httpStatus) {
        return buildResponse(exception.getMessage());
    }
}
