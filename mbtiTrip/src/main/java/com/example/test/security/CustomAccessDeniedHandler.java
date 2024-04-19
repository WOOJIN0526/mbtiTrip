package com.example.test.security;

import java.io.IOException;


import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;





@Log4j2
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
	 
	/**@author 신성진
	 *  인증 받은 사용자에 대한 FobidenException이 발생했을 때 처리해주는 예외 핸들러중 하나입니다. 
	 * */
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.error("Fobiden");
		log.error("requestURI =>{}", request.getRequestURI());
		
		//securityContextHolder에 내장 된, 사용자의 권한을 확인합니다.
		Authentication auths = SecurityContextHolder.getContext().getAuthentication();
		log.info("authenticationType  --> {}", auths.getClass());
		log.info("authentication --> {}", auths);
		
		String userAuth = null;
		String urlPath = null;
		if(auths != null && auths.isAuthenticated()) {
			for(GrantedAuthority auth : auths.getAuthorities()) {
				//현재 접속한 사용자의 권한을 불러옵니다.
				userAuth = auth.getAuthority();
			}
			switch(userAuth) {
			//현재 이용중인 사용자의 권한에 맞게 mainPage로 Rediirect 시킵니다.
			case "ROLE_USER" : response.sendRedirect("/user/main"); break;
			case "ROLE_BIS" : response.sendRedirect("/bis/main"); break;
			}
		}
	}

}
