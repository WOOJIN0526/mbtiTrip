package com.example.test.security;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Collection;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
	
	/**
	 * @author Shinsungjin 
	 * 로그인이 성공 했을 때 처리 될 Handelr입니다. 
	
	 * */
	
	
	@Autowired
	private  UserService userService;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	 
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
		@Override
		public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {
			try {	
				log.info("로그인 성공시 접속자의 권한 확인이 가능한지 에 대한  log {}", authentication.getAuthorities());
				
				//세션 정보를 호출 합니다.
				HttpSession session = request.getSession();
				//세션이 유지 될 최대 시간을 지정합니다. 
				session.setMaxInactiveInterval(60*60);
				//세션에 사용자의 권한 정보를 등록합니다.
				session.setAttribute("userrole", authentication.getAuthorities());
			
				//로그인 된 사용자의 권한 정보를 가져옵니다. 
				// 권한은 userDTO의 User_Role의 Enum 타입으로 관리됩니다.
				Collection<? extends GrantedAuthority> role = authentication.getAuthorities();
				//세션 정보 잘 저장 됨 
				log.info("role 정보{}", role.toString());
				//사용자의 권한 정보에 맞춰, 각각의 login-Proccess를 실행하고, 각각의 main으로 redirecet 합니다. 
				switch (role.toString()) {
				case "[ROLE_USER]" : 
					response.sendRedirect("user/login/success");
					break;
				case "[ROLE_BIS]":
					response.sendRedirect("bis/login/success");
					break;
				case "[ROLE_ADMIN]":
					response.sendRedirect("admin/login/success");
					break;
				}
			
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
}
