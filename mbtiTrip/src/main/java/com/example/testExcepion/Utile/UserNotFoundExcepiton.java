package com.example.testExcepion.Utile;

import lombok.Getter;

@Getter
public class UserNotFoundExcepiton extends RuntimeException {
	
	private final UtileExceptionCode utileExceptionCode;
	
	public UserNotFoundExcepiton(UtileExceptionCode utileExceptionCode) {
		super(utileExceptionCode.getMessage());
		this.utileExceptionCode = utileExceptionCode;
	}
}
