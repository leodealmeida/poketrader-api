package com.poketrader.api.infrastructure.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final int status;
    private final String message;

    @Data
    private static class ValidationError {
        private final String field;
        private final String message;
    }

}

