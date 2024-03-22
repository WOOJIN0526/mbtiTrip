package com.example.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorRespone {

	private  int status;
	private  String message;
	private String code;
	
	
	ErrorRespone(ErrorCode errorCode){
		this.status = errorCode.getStatus();
		this.message = errorCode.getMessage();
		this.code = errorCode.getErrorCode();
	}
	
}
