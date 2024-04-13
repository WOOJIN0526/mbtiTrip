package com.example.testExcepion.updated;

import org.codehaus.groovy.syntax.SyntaxException;
import org.mybatis.spring.MyBatisSystemException;

import lombok.Getter;

@Getter
public class UpdateException extends RuntimeException {
	
	private final UpdateExceptionEnum updateExceptionCode;
	
	public UpdateException(UpdateExceptionEnum updateExceptionCode) {
		super(updateExceptionCode.getMessage());
		this.updateExceptionCode = updateExceptionCode;
	}
}
