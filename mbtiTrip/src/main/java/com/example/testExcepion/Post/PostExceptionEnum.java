package com.example.testExcepion.Post;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum PostExceptionEnum implements ExceptionCode{
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST_001", "게시물을 찾을 수 없습니다."),
    POST_UNABLE_TO_CREATE(HttpStatus.INTERNAL_SERVER_ERROR, "POST_002", "게시물을 생성할 수 없습니다."),
    POST_UNABLE_TO_UPDATE(HttpStatus.INTERNAL_SERVER_ERROR, "POST_003", "게시물을 업데이트할 수 없습니다."),
    POST_UNABLE_TO_DELETE(HttpStatus.INTERNAL_SERVER_ERROR, "POST_004", "게시물을 삭제할 수 없습니다."),
    POST_INVALID_POST_DATA(HttpStatus.BAD_REQUEST, "POST_005", "유효하지 않은 게시물 데이터입니다."),
    POST_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "POST_006", "권한이 없습니다."),
    POST_UNABLE_TO_TITLE(HttpStatus.INTERNAL_SERVER_ERROR, "POST_007", "제목에 특수문자를 넣을 수 없습니다."),
    POST_UNABLE_TO_TITLE2(HttpStatus.INTERNAL_SERVER_ERROR, "POST_008", "중복 된 제목이 존재합니다."),
    POST_UNABLE_TO_TITLE3(HttpStatus.INTERNAL_SERVER_ERROR, "POST_009", "제목은 15글자 이하로 적어주세요"),
    POST_UNABLE_TO_TITLE4(HttpStatus.INTERNAL_SERVER_ERROR, "POST_009", "제목은 15글자 이하로 적어주세요"),
    POST_UNABLE_TO_ContentsNULLPOINT(HttpStatus.INTERNAL_SERVER_ERROR, "POST_A010", "내용이 너무 많습니다."),
    POST_UNABLE_TO_ContentsSize(HttpStatus.INTERNAL_SERVER_ERROR, "POST_010", "내용이 너무 많습니다."),
    POST_NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR, "POST_011", "사용자 정보를 불러올 수 없습니다."),
	POST_UNKNOWN_SEVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "POST_012", "알수 없는 오류가 발생했습니다. 잠시후 다시 시도해주세요 ");
	
	private HttpStatus status;
	private String code;
	private String message;

	private PostExceptionEnum(HttpStatus status, String code, String message) {
		  this.status = status;
	      this.code = code;
	      this.message = message;
	}
	
	
}
