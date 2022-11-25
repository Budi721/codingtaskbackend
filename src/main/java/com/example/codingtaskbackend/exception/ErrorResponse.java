package com.example.codingtaskbackend.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {
    private final Integer statusCode;
    private final String message;

    public ErrorResponse(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    @JsonProperty(value = "status_code")
    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
