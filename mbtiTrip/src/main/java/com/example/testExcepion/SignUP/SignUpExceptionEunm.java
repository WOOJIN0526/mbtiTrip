package com.example.testExcepion.SignUP;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum SignUpExceptionEunm implements ExceptionCode{
	SignUP_DUPLICATION_MEMBER(HttpStatus.BAD_REQUEST, "SIGN_001", "이미 존재하는 회원입니다."),
	SignUP_MISMATCH(HttpStatus.BAD_REQUEST, "SIGN_002", "잘못 된 요청입니다."),
	Signup_DUPLCATION_ID(HttpStatus.BAD_REQUEST , "SIGN_003", "이미존재하는 ID 입니다."),
	Signup_BADPASSWORD(HttpStatus.BAD_REQUEST, "SIGN_004", "비밀번호를 다시작성 해주세요"),
	SignUP_DUPLICATION_EMAIL(HttpStatus.BAD_REQUEST,"SIGN_005", "이미 존재하는 email입니다."),
	SignUP_Bad_NiCKNAME(HttpStatus.BAD_REQUEST, "SIGN_006", "닉네임에 특수문자는 사용할 수 없습니다."),
	Signup_DUPLCATION_NiCKNAME(HttpStatus.BAD_REQUEST, "SIGN_007", "중복된 닉네임입니다.");
	private final HttpStatus status;
    private final String code;
    private final String message;

    SignUpExceptionEunm(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
