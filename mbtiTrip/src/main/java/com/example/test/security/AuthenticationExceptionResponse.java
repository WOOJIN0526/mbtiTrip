package com.example.test.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;

import com.example.testExcepion.Utile.UtileExceptionCode;

import groovy.transform.ToString;
import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@ToString
public class AuthenticationExceptionResponse {

	private HttpStatus status;
	private String message;

	AuthenticationExceptionResponse(){
		this.status = null;
		this.message= null;
	}
	
	AuthenticationExceptionResponse(UtileExceptionCode utileExceptionCode){
		this.status = utileExceptionCode.getStatus();
		this.message = utileExceptionCode.getMessage();
	}

}
