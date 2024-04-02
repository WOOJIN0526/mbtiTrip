package com.example.test.security;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.RememberMeAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.RememberMeTokenAlgorithm;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.example.test.User.DTO.User_Role;
import com.example.test.User.Service.CustomLoginService;

import com.example.test.User.Service.UserService;
import com.mysql.cj.protocol.AuthenticationProvider;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@Log4j2
@EnableWebSecurity(debug = false)
@EnableMethodSecurity(mode =  AdviceMode.PROXY)
@Configuration
@RequiredArgsConstructor
public class Security_Config  {

	private UserDetailsService userDetailsService; //?
	private CustomLoginService CustomloginService; 
	
	
	public Security_Config(CustomLoginService loginService) {
		this.CustomloginService = loginService;
	}
	
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public void configure(WebSecurity web) throws Exception {
//	    (web)->web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()
//	}
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("**.js", "**.css", "**.img");
     }

	@Bean
    protected SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
       .authorizeHttpRequests((authorizeHttpRequests)-> authorizeHttpRequests
                	 .requestMatchers("/login_A", "/**", "/user/signup", "/bis/signup")
                	 .permitAll());
       http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
    		   .requestMatchers("/user/**", "bis/**", "/admin/**").hasRole("ADMIN")
    		   .requestMatchers("bis/**", "/user/**").hasAnyRole("BIS")
    		   .requestMatchers("/user/**").hasAnyRole("USER")
    		   .requestMatchers("/**").permitAll()
    		   )
    	.formLogin((formLogin) -> formLogin
    			.loginPage("/login_A")
    			.successHandler(new CustomSuccessHandler())
    			.failureHandler(new CustomLoginFailhandelr()))
    			;
    	http.rememberMe((remember) -> remember
    			.key("userName")
    			.rememberMeParameter("remember-me")
    			.tokenValiditySeconds(86400)
    			.userDetailsService(userDetailsService)
    			.alwaysRemember(false))
    			;
    	http.logout((logout)-> logout
    			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    			.logoutSuccessUrl("/")
    			.deleteCookies("JSESSIONID")
    			.clearAuthentication(true)
    			);
    	
    	http.sessionManagement((SessionManagement) ->SessionManagement
    			.sessionFixation().changeSessionId()
    			.maximumSessions(1)
//    			.expiredSessionStrategy(customSessionExpiredStrategy)
    			.maxSessionsPreventsLogin(true) //새로운 요청 거부 
    			
    			) ;
    
    					
//    		    .usernameParameter("userId")
//    		    .passwordParameter("password")
//    		    .successHandler((request, response, authentication)->{
//    		    	//3.28 TEST 진행 예정 
//    		    	// role 을 잘 받아오면, 여기서 format 가능 
//    		    	String role = authentication.getAuthorities().toString();
//    		    	log.info("로그인 성공시 유저의 권한 확인 "+role);
//    		    	log.info("principal 정보" + authentication.getPrincipal().toString());
//    		    	System.out.println("authentication" + authentication.getName());
//    		    	response.sendRedirect(String.format("user/mypage/%s"));
//    		    })
    	
//    		    .failureHandler((request, response, exception)->{s
//    		        System.out.println("exception : " + exception.getMessage());
//    		    	response.sendRedirect("login_A");
//    		    })
    		    
//    	.exceptionHandling((ex)->ex.accessDeniedPage("/access_denied_page"))
    	
//    	.rememberMe((rememberMe)->rememberMe   
//    			.rememberMeParameter("remember")
//    			.tokenValiditySeconds(60300)
//    			.alwaysRemember(true))
//    	.logout((logout)->logout
//    		.logoutSuccessUrl("/")
//    		.invalidateHttpSession(true))
//    		 .exceptionHandling((exceptionHandling)-> exceptionHandling
//    		.accessDeniedPage("/accessDenied")))   
//       ;
	 
//        http
//        .rememberMe((remember)->remember
//        		.rememberMeServices(rememberMeServices(userDetailsService)))
//        		;
       
        // login Page 분할 방안 고려 
//        http.formLogin((formLogin) -> formLogin 
//        		.loginProcessingUrl("login/user")
//        		.loginPage("/login_form")
//        		);
        
//        
//       http.rememberMe(
//    		   (rememberMe) -> rememberMe
//    		   .rememberMeParameter("remember")   // login 시 기억할 것인지에 대한 chk box name 값
//    		   .tokenValiditySeconds(3600)  //토큰 유효 시간 
//    	       .alwaysRemember(true)
//    	       .userDetailsService(userDetailsService)
//    		   );
//         http.formLogin((formLogin)->formLogin
//        		.loginPage("/login_form")
//                .permitAll())
//        		.securityContext((securityContext)->securityContext
//        				.securityContextRepository(new DelegatingSecurityContextRepository(
//        						new RequestAttributeSecurityContextRepository()
//        						,new HttpSessionSecurityContextRepository()
//        						)));
        
//        http
//        .sessionManagement((sessionManagement)->sessionManagement.invalidSessionUrl("/login_A"))
//        .logout((logout)->logout
//        		.logoutUrl("/logout")
//        		.addLogoutHandler((request, response, authtication) -> {
//        			HttpSession session = request.getSession();
//        			if(session != null) {
//        				session.invalidate();
//        			}
//        		})
//        		.logoutSuccessHandler((request, response, authtication) -> {
//        			response.sendRedirect("/");
//        		})
//        		.deleteCookies("mbox", "remember-me"));
        		
//        		/3/26 위 코드 작성[아래는 로그아웃을 간단하게 구현한 ver 예시 
//        	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .permitAll());
        
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/**").csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse())));

	
        //defalutURL 수정 or 삭제 필요 테스트 이후 진행 작업자: 신성진 
        return http.build();

    }

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(CustomloginService).passwordEncoder(passwordEncoder());
    }
//	
    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
    	RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
    	TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("key", userDetailsService, encodingAlgorithm);
    	rememberMe.setMatchingAlgorithm(RememberMeTokenAlgorithm.MD5);
    	return rememberMe;
    }
    
    @Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) 
	throws Exception{
		
		return authenticationConfiguration.getAuthenticationManager();
	}
    
    /** 구현 중 */
//    @Bean
//    RememberMeAuthenticationFilter rememberMeFilter() {
//        RememberMeAuthenticationFilter rememberMeFilter = new RememberMeAuthenticationFilter(null, null);
//        rememberMeFilter.setRememberMeServices(rememberMeServices());
//        rememberMeFilter.setAuthenticationManager(theAuthenticationManager);
//        return rememberMeFilter;
//    }
//
//    @Bean
//    TokenBasedRememberMeServices rememberMeServices() {
//        TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices("key", userDetailsService);
//        rememberMeServices.setUserDetailsService(myUserDetailsService);
//        rememberMeServices.setKey("springRocks");
//        return rememberMeServices;
//    }
//
//    @Bean
//    RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
//        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider("key");
//        rememberMeAuthenticationProvider.setKey("springRocks");
//        return rememberMeAuthenticationProvider;
//    }
    
    
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
	

}
