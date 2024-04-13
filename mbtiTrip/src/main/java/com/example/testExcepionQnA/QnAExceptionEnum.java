package com.example.testExcepionQnA;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum QnAExceptionEnum {
	QnA_NOT_FOUND_USER(HttpStatus.NOT_FOUND, "QNA_001", "유저정보를 찾을 수 없습니다."),
	QnA_NOT_INSERT(HttpStatus.BAD_REQUEST, "QnA_002", "정상적으로 저장되지 않았습니다. 다시 한번 확인해주세요"),
	QnA_NOT_TITLE(HttpStatus.NOT_FOUND, "QnA_003", "질문 제목이 비었습니다. 다시 작성해주세요"),
	QnA_NOT_CONTENTS(HttpStatus.NOT_FOUND, "QnA_003", "질문 내용이 비었습니다. 다시 작성해주세요"),
	QnA_INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "QNA_003", "QnA 서버에서 내부 오류가 발생했습니다. 잠시후 다시 시도해주세요");
	
	private final HttpStatus status;
    private final String code;
    private final String message;

    QnAExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
