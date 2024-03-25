package com.example.test;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.example.test.User.DTO.User_Role;
import com.mysql.cj.protocol.AuthenticationProvider;

import lombok.RequiredArgsConstructor;


@EnableWebSecurity(debug = false)
@EnableMethodSecurity(mode =  AdviceMode.PROXY)
@Configuration
@RequiredArgsConstructor
public class Security_Config  {
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/user/**", "/bis/**", "/admin/**").anyRequest();
     }

	@Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests)-> authorizeHttpRequests
                		 .requestMatchers("/login_form", "static/**", "/**").permitAll()
                         .anyRequest( ).authenticated());
	
        http.formLogin((formLogin)->formLogin
        		.loginPage("/login_form")
                .loginProcessingUrl("/login_A")
                .defaultSuccessUrl("/")
                .failureForwardUrl("/login_A")
                .permitAll());
        
        
        http
        .sessionManagement((sessionManagement)->sessionManagement.invalidSessionUrl("/login_A"))
        .logout((logout)->logout
        	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll());
  
        ;
        
        
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/**").csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse())));
        return http.build();
        
		//위는 테스 트 끝.	
        //defalutURL 수정 or 삭제 필요 테스트 이후 진행 작업자: 신성진 
    }
//	@Bean
//	SecurityFilterChain  filterChain(HttpSecurity htsp) throws Exception{
//		
//		http
//		.authorizeHttpRequests( //HTTP 인가 설정 
//				(authorizeHttpRequests) -> 
//				 authorizeHttpRequests
//				 .requestMatchers(
//				  new AntPathRequestMatcher("/**")).permitAll()
//				)
//		;

	
			
		 
//		http
//		.authorizeHttpRequests((authorizeHttpRequests) -> 
//		 authorizeHttpRequests
//		 .requestMatchers(
//		  new AntPathRequestMatcher("/user/**")).denyAll())
//			.formLogin((formLogin) -> formLogin.loginPage("login_form"))
//			
//		.logout((logout) -> logout.logoutSuccessUrl("/"))
//		;s
	
//		http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
//				.requestMatchers(new AntPathRequestMatcher("/mypage")).denyAll());
//		
//		return http.build();
//		
//	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
