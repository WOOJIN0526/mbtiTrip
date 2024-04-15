package com.example.testExcepion.SignUP;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.testExcepion.ExceptionCode;
import com.example.testExcepion.ExceptionResponse;
import com.example.testExcepion.login.LoginExceptionEnum;

import lombok.Getter;



@Getter
public class SignUpException extends RuntimeException {
	private final SignUpExceptionEunm signupExceptionEnum;

	
    public SignUpException(SignUpExceptionEunm exceptionCode) {
	        super(exceptionCode.getMessage());
	        this.signupExceptionEnum = exceptionCode;
	}
    

}
