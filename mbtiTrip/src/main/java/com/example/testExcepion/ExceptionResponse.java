package com.example.testExcepion;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final HttpStatus status;
    private final String code;
    private final String message;

    public ExceptionResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getStatus();
        this.code = exceptionCode.getCode();
        this.message = exceptionCode.getMessage();
    }
}
