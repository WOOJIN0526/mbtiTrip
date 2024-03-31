package com.example.test;

import java.io.IOException;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.DTO.User_Role;
import com.example.test.User.Service.UserService;
import com.example.test.User.Service.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private  UserService userService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			try {	
				log.info("로그인 성공시 접속자의 권한 확인이 가능한지 에 대한  log {}", authentication.getAuthorities());
				HttpSession session = request.getSession();
				String userName = authentication.getName();
				session.setAttribute("userName", userName);
				response.sendRedirect("user/login/success");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}
