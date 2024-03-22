package com.example.test;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import groovy.util.logging.Slf4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(Exception ex) {
    	log.error("handlerException", ex);
    	ErrorRespone response = new ErrorRespone(ErrorCode.INTER_SERVER_ERROR);
    	return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);

       
    }
}






