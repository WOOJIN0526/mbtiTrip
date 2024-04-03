package com.example.test;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
	NOT_FOUND(404,"COMMON-ERR-404","페이지가 동작하지 않는다.."),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","해당 ID는 존재하지 않습니다."),
    BAD_REQUEST(400, "BAD_REQUEST", "잘 못 된 요청입니다")
    ;
	
	private int status;
	private String errorCode;
	private String message;
}
