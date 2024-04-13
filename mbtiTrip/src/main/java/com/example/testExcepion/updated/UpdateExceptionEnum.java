package com.example.testExcepion.updated;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum UpdateExceptionEnum implements ExceptionCode{
	UPDATE_FAIL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "UPDATE_001", "업데이트가 정상적으로 저장되지 않았습니다. 잠시후 다시 시도해주세요"),
	UPDATE_FAIL_BADREQUEST(HttpStatus.BAD_REQUEST, "UPDATE_002", "올바른 양식인지 다시 확인해주세요");
	
	private HttpStatus status;
	private String code;
	private String message;
	
	private UpdateExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
	
}
