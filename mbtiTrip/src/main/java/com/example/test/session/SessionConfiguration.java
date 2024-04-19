package com.example.test.session;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionConfiguration {
/**
 * @author Shinsungjin 
 * 현재 이용 중인 사용자수를 구현하기 위한 session 설정파일입니다.
 * */
	
	
    @Bean
    public ServletListenerRegistrationBean<SessionUserCnt> sessionUserCntListener() {
        return new ServletListenerRegistrationBean<>(new SessionUserCnt());
    }
}