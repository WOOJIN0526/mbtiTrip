package com.example.testExcepion.SignUP;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum SignUpExceptionEunm implements ExceptionCode{
	SignUP_DUPLICATION_MEMBER(HttpStatus.BAD_REQUEST, "SIGN_001", "이미 존재하는 회원입니다."),
	SignUP_MISMATCH(HttpStatus.BAD_REQUEST, "SIGN_002", "잘못 된 요청입니다."),
	Signup_DUPLCATION_ID(HttpStatus.BAD_REQUEST , "SIGN_003", "이미존재하는 ID 입니다."),
	Signup_BADPASSWORD(HttpStatus.BAD_REQUEST, "SIGN_004", "비밀번호가 너무 짧습니다."),
	SignUP_DUPLICATION_EMAIL(HttpStatus.BAD_REQUEST,"SIGN_005", "이미 존재하는 email입니다."),
	SignUP_Bad_NiCKNAME(HttpStatus.BAD_REQUEST, "SIGN_006", "닉네임에 특수문자는 사용할 수 없습니다."),
	Signup_DUPLCATION_NiCKNAME(HttpStatus.BAD_REQUEST, "SIGN_007", "중복된 닉네임입니다."),
	SIGN_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "SIGN_008", "서버에서 내부 오류가 발생했습니다. 잠시후 다시 시도해주세요"),
	SIGN_ID_NULL(HttpStatus.INTERNAL_SERVER_ERROR, "SIGN_009", "아이디를 입력해주세요"),
	SIGN_NICKNAME_NULL(HttpStatus.INTERNAL_SERVER_ERROR, "SIGN_010", "닉네임을 입력해주세요"),
	SIGN_PASSWORD_NULL(HttpStatus.BAD_REQUEST, "SIGN_011", "비밀 번호를 입력해주세요 "),
	SIGN_EMAIL_NULL(HttpStatus.BAD_REQUEST, "SIGN_012", "메일을 입력해주세요")
	;
	
	
	
	private final HttpStatus status;
    private final String code;
    private final String message;

    SignUpExceptionEunm(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
