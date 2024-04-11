package com.example.testExcepion.Cart;

import org.springframework.http.HttpStatus;

import com.example.testExcepion.ExceptionCode;

import lombok.Getter;

@Getter
public enum CartExceptionEnum implements ExceptionCode {
	CART_NOTFOUND_ITEM(HttpStatus.NOT_FOUND, "CART_001", "존재하지 않는 정보입니다."),
	CART_STARTDATE_MISMATCH(HttpStatus.BAD_REQUEST, "CART_002", "시작일이 이미 지났습니다."),
	CART_ENDdDATE_MISMATCH(HttpStatus.BAD_REQUEST, "CART_003", "종료일이 이미 지났습니다.");
	
	private final HttpStatus status;
    private final String code;
    private final String message;

    CartExceptionEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
