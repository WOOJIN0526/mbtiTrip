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
import org.springframework.security.web.access.AccessDeniedHandler;
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
	/**
	 * @author Shin-SungJin 
	 *  security 설정 파일입니다.
	 * */
	

	private UserDetailsService userDetailsService; 
	private CustomLoginService CustomloginService; 
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	private CustomAccessDeniedHandler customAccessDeniedHandelr;
	
	
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
        http // 해당 URL에 대한 권한을 열어둡니다.
       .authorizeHttpRequests((authorizeHttpRequests)-> authorizeHttpRequests
                	 .requestMatchers("/login_A", "/**", "/user/signup", "/bis/signup")
                	 .permitAll());
        //아래에 포함 되어 있는 URL은 인증이필요한 URL 입니다.
       http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
    		   .requestMatchers("/").anonymous()
    		   .requestMatchers("/user/**", "bis/**", "/admin/**").hasRole("ADMIN")
    		   .requestMatchers("bis/**", "/user/**").hasAnyRole("BIS")
    		   .requestMatchers("/user/**").hasAnyRole("USER")
    		   )
       //로그인에 대한 설정을 진행합니다
    	.formLogin((formLogin) -> formLogin
    			.loginPage("/login_A") //기본 URL 설정
    			.successHandler(new CustomSuccessHandler()) //로그인 성공시 진행 될 process
    			.failureHandler(new CustomLoginFailhandelr()) //로그인 실패시 진행 될 process
    			.failureUrl("/login_A") //로그인 실패시 이동할 경로 
    			)
    			;
       //자동 로그인에 대한 설정입니다.
    	http.rememberMe((remember) -> remember
    			.key("userName")  //자동로그인 Key값
    			.rememberMeParameter("remember-me") // loginPage에서 check 박스 value입니다.
    			.tokenValiditySeconds(86400) //자동 로그인 token의 유효시간 입니다 (초)
    			.userDetailsService(userDetailsService)
    			.alwaysRemember(false) //자동로그인을 항시 켜둘지 설정합니다., -> false - > cKBox가 활성화 되어야 실행 
    			)
    			;
    	
    	//로그 아웃에 대한 설정입니다
    	http.logout((logout)-> logout
    			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //logOut 경로 설정
    			.logoutSuccessUrl("/")    //성공시 이동 경로
    			.deleteCookies("JSESSIONID") // JesssionID를 삭제합니다.
    			.clearAuthentication(true)  //권한도 삭제합니다.
    			);
    	
    	//Session관리를 설정합니다. 
    	http.sessionManagement((SessionManagement) ->SessionManagement
    			.sessionFixation().changeSessionId()
    			.maximumSessions(1)  //동시에 접속할 수 있는 사용자 수를 컨트롤 합니다. 
    			.maxSessionsPreventsLogin(false) //새로운 요청 거부 
    			.expiredSessionStrategy(new customSessionExpiredStrategy())	//현재 customSession과 설정 충돌이 의심되오나, 일정 부족으로 인해 하지 못했습니다. 
    			) ;
    	
    	//웹 상에서 일어나는 Exception에 대한 설정입니다.
    	http.exceptionHandling()
        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) //인증되지 않은 상태의 FobidenEx
        .accessDeniedHandler(new CustomAccessDeniedHandler()) //인증 된 사용자의 FobidenEx
    	;
    					
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
        
    	//csrf허용에 대한 설정입니다. 
        http.csrf((csrf)->csrf.ignoringRequestMatchers("/**").csrfTokenRepository((CookieCsrfTokenRepository.withHttpOnlyFalse())));

        return http.build();

    }

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(CustomloginService).passwordEncoder(passwordEncoder());
    }
	
//	
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return this.customAccessDeniedHandelr;
	}
	
	
	//rememberMe 관련 설정
    @Bean
    RememberMeServices rememberMeServices(UserDetailsService userDetailsService) {
    	RememberMeTokenAlgorithm encodingAlgorithm = RememberMeTokenAlgorithm.SHA256;
    	TokenBasedRememberMeServices rememberMe = new TokenBasedRememberMeServices("key", userDetailsService, encodingAlgorithm);
    	//MD5 방식의 rememberMeToken을 밝급합니다.
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
//		;
	
//		http.authorizeHttpRequests((authorizeHttpRequests)->authorizeHttpRequests
//				.requestMatchers(new AntPathRequestMatcher("/mypage")).denyAll());
//		
//		return http.build();
//		
//	}
	

}
