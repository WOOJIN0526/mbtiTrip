package com.example.test.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.log4j.Log4j2;


@Log4j2
@WebListener
public class SessionUserCnt implements HttpSessionListener{
	
	/**
	 * @author Shinsungjin 
	 * 현재 이용 중인 사용자수를 구현하기 위한 session 설정 구현파일입니다.
	 * */
	
	public static int cnt;
	
	public int getCnt() {
		return this.cnt;
	}

	
	//사용자가 session에 등록 되면 session 이용자수를 증가시킵니다.
	@Bean
	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		cnt++;
		log.info("SessionCreated => {} , totalAcccses =>{}", session, cnt);
	}

	//사용자가 session에 빠져나가면 session 이용자수를 감소시킵니다.
	@Bean
	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		cnt = (cnt > 0) ? cnt-- : 0;  //cnt가 0일 경우는 0으로 초기화 합니다.
		HttpSession session = evt.getSession();
		log.info("SessionDestroyed => {}, totalacces =>{}", session, cnt);
	}
	
	
}


