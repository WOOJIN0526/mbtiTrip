package com.example.testExcepionQnA;

import lombok.Getter;

@Getter
public class QnAException extends RuntimeException {
	private final QnAExceptionEnum qnaExceptionCode;
	
	
	public QnAException(QnAExceptionEnum qnaExceptionCode){
		super(qnaExceptionCode.getMessage());
		this.qnaExceptionCode = qnaExceptionCode;
	}
	
	
}
