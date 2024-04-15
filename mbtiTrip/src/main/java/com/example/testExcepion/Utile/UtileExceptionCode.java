package com.example.testExcepion.Utile;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum UtileExceptionCode implements ExceptionCode {	
	
	/*
	 * 일반적으로 웹 서버에서 일어날 수 있는 Exception을 담아둔 Enum입니다. 
	 * 해당 ENUM을 다양한 예외 커스텀에 활용할 예정입니다. 
	 */
	
	
	USER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "UTITLE_001", "사용자를 찾을 수 없습니다. 다시 로그인해주세요"),
	USER_INVALID_INPUT(HttpStatus.BAD_REQUEST, "UTITLE_002", "입력이 올바르지 않습니다."),
	USER_UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "UTITLE_004", "인증되지 않은 요청입니다."),
	USER_FORBIDDEN(HttpStatus.FORBIDDEN, "UTITLE_005", "해당 작업에 대한 권한이 없습니다."),
	UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "UTITLE_006", "지원되지 않는 미디어 타입입니다."),
	SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "UTITLE_007", "일시적으로 서비스를 이용할 수 없습니다.");
	
	private HttpStatus stauts;
	private String code;
	private String message;

	 
	UtileExceptionCode(HttpStatus status, String code, String message){
		this.stauts = status;
		this.code = code;
		this.message = message;
	}
	
	@Override
	public HttpStatus getStatus() {
		return this.getStatus();
	}


}
