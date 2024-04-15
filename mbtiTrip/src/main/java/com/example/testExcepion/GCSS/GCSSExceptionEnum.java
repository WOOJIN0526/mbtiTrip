package com.example.testExcepion.GCSS;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;


@Getter
public enum GCSSExceptionEnum implements ExceptionCode{
	GCSS_FILE_NOTFOUND(HttpStatus.NOT_FOUND, "GCSS_001", "파일을 찾을 수 없습니다."),
	GCSS_UNABLE_TO_UPLOAD(HttpStatus.INTERNAL_SERVER_ERROR, "GCSS_002", "파일을 업로드할 수 없습니다."),
	GCSS_INVALID_PARAMETERS(HttpStatus.BAD_REQUEST, "GCSS_004", "유효하지 않은 매개변수입니다."),
	GCSS_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "GCSS_005", "권한이 없습니다.");
	
	
	private HttpStatus status;
	private String code;
	private String message;

	
	private GCSSExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}
