package com.example.test.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.example.testExcepion.ExceptionResponse;
import com.example.testExcepion.Utile.UtileExceptionCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{

		
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		ObjectMapper obejectMapper = new ObjectMapper();
		
		log.error("requesetURL ==> {}", request.getRequestURI());
		
		AuthenticationExceptionResponse exResponse = new AuthenticationExceptionResponse(UtileExceptionCode.USER_FORBIDDEN);
		
		response.setStatus(exResponse.getStatus().value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(obejectMapper.writeValueAsString(exResponse.getMessage()));

	}

}
