package com.example.test.session;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionConfiguration {

    @Bean
    public ServletListenerRegistrationBean<SessionUserCnt> sessionUserCntListener() {
        return new ServletListenerRegistrationBean<>(new SessionUserCnt());
    }
}