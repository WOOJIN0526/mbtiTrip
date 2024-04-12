package com.example.testExcepion.login;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum LoginExceptionEnum implements ExceptionCode{
	
	LOGIN_NOTFOUND_MEMBER(HttpStatus.UNAUTHORIZED, "LOGIN_001", "존재하지 않는 회원입니다."),
	LOGIN_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "LOGIN_002", "아이디나 비밀번호가 맞지 않습니다."),
    LOGIN_NOT_AUTHENTICATION(HttpStatus.BAD_REQUEST, "LOGIN_003","권한이 없는 사용자입니다. 관리자에게 문의해주세요");
	
	private final HttpStatus status;
    private final String code;
    private final String message;

    LoginExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
