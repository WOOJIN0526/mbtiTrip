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
public class CustomAccessDeniedHandler implements AccessDeniedHandler{@Override
	
	
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		log.error("Fobiden");
		log.error("requestURI =>{}", request.getRequestURI());
	
		Authentication auths = SecurityContextHolder.getContext().getAuthentication();
		log.info("authenticationType  --> {}", auths.getClass());
		log.info("authentication --> {}", auths);
		
		String userAuth = null;
		
		if(auths != null && auths.isAuthenticated()) {
			for(GrantedAuthority auth : auths.getAuthorities()) {
				userAuth = auth.getAuthority();
			}
		}
		
		log.info("user Redirect =>{}", userAuth);
		response.sendRedirect(String.format("/%s/main", userAuth.split("_")));
				
	}

}
