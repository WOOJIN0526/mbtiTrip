package com.example.testExcepion.SignUP;

import com.example.testExcepion.login.LoginExceptionEnum;

public class SignUpException extends RuntimeException {
	private final SignUpExceptionEunm signupExceptionEnum;
	
    public SignUpException(SignUpExceptionEunm signupExceptionEnum) {
	        super(signupExceptionEnum.getMessage());
	        this.signupExceptionEnum = signupExceptionEnum;
	}
}
