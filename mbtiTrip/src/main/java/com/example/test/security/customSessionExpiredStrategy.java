package com.example.test.security;

import java.io.IOException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class customSessionExpiredStrategy implements SessionInformationExpiredStrategy{

	/** 중복로그인 방지를 위한 클래스입니다.
	 * */
	
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletRequest request = event.getRequest();
		HttpServletResponse response = event.getResponse();
		log.info("message customSessionExpiredStrategy {}", event.getRequest());
		log.info("message customSessionExpiredStrategy {}", event.getResponse());
		
		
		request.setAttribute("DUPLICATE_LOGIN", true);
		
		request.getRequestDispatcher("/login_A").forward(request, response);
		
	}

}
