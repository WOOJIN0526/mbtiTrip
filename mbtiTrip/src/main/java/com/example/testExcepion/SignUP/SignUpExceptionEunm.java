package com.example.testExcepion.SignUP;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum SignUpExceptionEunm implements ExceptionCode{
	SignUP_DUPLICATION_MEMBER(HttpStatus.BAD_REQUEST, "SIGN_001", "이미 존재하는 회원입니다."),
	SignUP_MISMATCH(HttpStatus.BAD_REQUEST, "SIGN_002", "잘못 된 정보입니다.");
    
	private final HttpStatus status;
    private final String code;
    private final String message;

    SignUpExceptionEunm(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
