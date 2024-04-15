package com.example.testExcepion.Utile;

import lombok.Getter;

@Getter
public class AuthorizedException extends SecurityException{
	
	private final UtileExceptionCode utileExceptionCode;

	public AuthorizedException(UtileExceptionCode utileExceptionCode) {
		super(utileExceptionCode.getMessage());
		this.utileExceptionCode = utileExceptionCode;
	}
	
}
