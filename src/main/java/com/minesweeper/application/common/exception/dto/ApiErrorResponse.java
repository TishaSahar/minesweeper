package com.minesweeper.application.common.exception.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ApiErrorResponse(
        @JsonProperty("error")
        String error
) {}
