package com.example.testExcepion.Insert;

import org.apache.ibatis.session.SqlSessionException;
import org.codehaus.groovy.syntax.SyntaxException;

import lombok.Getter;


@Getter
public class InsertException extends SqlSessionException{
	private final InsertExceptionEnum insertExceptionEnum;
	
	public InsertException(InsertExceptionEnum insertExceptionEnum) {
		super(insertExceptionEnum.getMessage());
		this.insertExceptionEnum = insertExceptionEnum;
	}
}
