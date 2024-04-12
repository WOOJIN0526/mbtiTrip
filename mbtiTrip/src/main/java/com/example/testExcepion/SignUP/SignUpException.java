package com.example.testExcepion.SignUP;

import com.example.testExcepion.ExceptionCode;
import com.example.testExcepion.ExceptionResponse;
import com.example.testExcepion.login.LoginExceptionEnum;

import lombok.Getter;



@Getter
public class SignUpException extends RuntimeException {
	private final SignUpExceptionEunm signupExceptionEnum;
	
    public SignUpException(SignUpExceptionEunm exceptionCode) {
	        super(exceptionCode.getMessage());
	        this.signupExceptionEnum = exceptionCode;
	}
}
