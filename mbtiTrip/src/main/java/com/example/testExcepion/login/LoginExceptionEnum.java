package com.example.testExcepion.login;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum LoginExceptionEnum implements ExceptionCode{
	
	LOGIN_NOTFOUND_MEMBER(HttpStatus.UNAUTHORIZED, "LOGIN_001", "존재하지 않는 회원입니다."),
	LOGIN_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "LOGIN_002", "비밀번호가 맞지 않습니다.");
    
	
	private final HttpStatus status;
    private final String code;
    private final String message;

    LoginExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
