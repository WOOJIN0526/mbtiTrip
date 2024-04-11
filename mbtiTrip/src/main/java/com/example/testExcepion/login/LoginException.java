package com.example.testExcepion.login;

public class LoginException extends RuntimeException{

	private LoginExceptionEnum loginExceptionEnum;
	
    public LoginException(LoginExceptionEnum loginExceptionCode) {
        super(loginExceptionCode.getMessage());
        this.loginExceptionEnum = loginExceptionCode;
    }
}
