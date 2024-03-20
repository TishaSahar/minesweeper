package com.tickets.application.common.exception.handler;

import com.tickets.application.common.exception.dao.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ApiErrorResponseCreator {

    public ApiErrorResponse buildResponse(String errorMessage, HttpStatus httpStatus) {
        return ApiErrorResponse.builder()
                .message(errorMessage)
                .timestamp(LocalDateTime.now())
                .httpStatusCode(httpStatus.value())
                .build();
    }

    public ApiErrorResponse buildResponse(Exception exception, HttpStatus httpStatus) {
        return buildResponse(exception.getMessage(), httpStatus);
    }
}
