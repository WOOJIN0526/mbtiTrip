package com.example.test.security;

import java.io.IOException;


import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.test.User.DTO.User_Role;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class WebAccessDeniedHandler implements AccessDeniedHandler{

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res,
			AccessDeniedException ade) throws IOException, ServletException {
	
		res.setStatus(HttpStatus.FORBIDDEN.value());
		
		if(ade instanceof AccessDeniedException) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication != null && (authentication.getAuthorities().contains(new SimpleGrantedAuthority(User_Role.user.getValue())))) {
				req.setAttribute("message", "접근 권한이 없는 사용자입니다.");
				req.setAttribute("nextPage", "/user");
				
			}
		}
		
	}

}
