package com.example.testExcepion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ErrorRespone {

	private  int status;
	private  String message;
	private String code;
	
	
	public ErrorRespone(ErrorCode errorCode){
		this.status = errorCode.getStatus();
		this.message = errorCode.getMessage();
		this.code = errorCode.getErrorCode();
	}
	
	
	public ErrorRespone(String err , String mesage){
		this.message = err;
		this.code = mesage;
	}
	
	
}
