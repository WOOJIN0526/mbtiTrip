package com.example.testExcepion.Insert;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum InsertExceptionEnum implements ExceptionCode{
	INSERT_SERVER_ERROR(HttpStatus.BAD_REQUEST, "insert_001", "정보가 제대로 저장되지 않았습니다"),
	INSERT_VALUE_ERROR(HttpStatus.BAD_REQUEST,"insert_002","입력값을 다시 확인해주세요");
	


	private final HttpStatus status;
    private final String code;
    private final String message;
    
    
    InsertExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
	
}
