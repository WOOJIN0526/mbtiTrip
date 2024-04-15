package com.example.testExcepion.Utile;

import lombok.Getter;

@Getter
public class NotSupportMediaTypeException extends RuntimeException {

	private final UtileExceptionCode utileExceptionCode;
	
	public NotSupportMediaTypeException(UtileExceptionCode utileExceptionCode) {
		super(utileExceptionCode.getMessage());
		this.utileExceptionCode = utileExceptionCode;
	}
}
