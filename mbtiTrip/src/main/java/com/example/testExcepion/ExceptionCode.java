package com.example.testExcepion;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public interface ExceptionCode {
	HttpStatus getStatus();
	String getCode();
	String getMessage();
}
