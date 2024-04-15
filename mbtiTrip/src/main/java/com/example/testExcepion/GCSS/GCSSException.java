package com.example.testExcepion.GCSS;

import java.io.IOException;

import lombok.Getter;

@Getter
public class GCSSException extends RuntimeException{

	private final GCSSExceptionEnum gcssExceptionCode;
	
	public GCSSException(GCSSExceptionEnum gcssExceptionCode) {
		super(gcssExceptionCode.getMessage());
		this.gcssExceptionCode = gcssExceptionCode;
	}
}
