package com.example.testExcepion.Item;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ItemExceptionEnum {
		ITEM_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "ITEM_001", "해당 정보를 업로드 하지 못했습니다."),
		ITEM_USER_NOT_FOUND(HttpStatus.NOT_FOUND, "ITEM_002", "해당 유저의 정보를 찾을 수 없습니다. 다시 로그인 해주세요"),
		ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "ITEM_003", "해당 아이템 정보를 찾을 수 없습니다."),
		ITEM_FILE_UPLOAD_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "ITEM_004", "파일 업로드에 실패하였습니다."),
		ITEM_ALREADY_EXISTS(HttpStatus.CONFLICT, "ITEM_005", "이미 존재하는 상품입니다."),
		ITEM_INVALID_DATA(HttpStatus.BAD_REQUEST, "ITEM_006", "유효하지 않은 데이터입니다."),
		ITEM_UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ITEM_007", "알 수 없는 오류가 발생하였습니다."),
		ITEM_INFORMATION_MISSING(HttpStatus.BAD_REQUEST, "ITEM_008", "상품 정보가 누락되었습니다."),
		ITEM_TITLE_MISMATCH(HttpStatus.BAD_REQUEST, "ITEM_009", "상품 이름이 누락되었습니다."),
		ITEM_TITLE_VALIDTION(HttpStatus.BAD_REQUEST, "ITEM_010", "상품 이름에 특수문자는 사용하지 못합니다."),
		ITEM_TITLE_SIZEMISS(HttpStatus.BAD_REQUEST, "ITEM_011", "상품 이름이 너무 깁니다. N자 이내로 적어주세요"),	
		ITEM_CONTENTS_SIZEMISS(HttpStatus.BAD_REQUEST, "ITEM_012", "상품 정보가 너무 깁니다. N자 이내로 적어주세요");
	
	
	
		private HttpStatus status; 
		private String code;
		private String message;
		
		
		ItemExceptionEnum(HttpStatus status, String code, String message) {
	        this.status = status;
	        this.code = code;
	        this.message = message;
	    }
}
