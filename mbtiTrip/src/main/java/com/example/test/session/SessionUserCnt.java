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
	
	public static int cnt;
	
	public int getCnt() {
		return this.cnt;
	}

	@Bean
	@Override
	public void sessionCreated(HttpSessionEvent evt) {
		HttpSession session = evt.getSession();
		cnt++;
		log.info("SessionCreated => {} , totalAcccses =>{}", session, cnt);
	}

	@Bean
	@Override
	public void sessionDestroyed(HttpSessionEvent evt) {
		cnt = (cnt > 0) ? cnt-- : 0; 
		HttpSession session = evt.getSession();
		log.info("SessionDestroyed => {}, totalacces =>{}", session, cnt);
	}
	
	
}


