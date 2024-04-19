package com.example.test.User.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.DTO.User_Role;
import com.example.testExcepion.login.LoginException;
import com.example.testExcepion.login.LoginExceptionEnum;

import groovy.cli.Option;
import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class CustomLoginService implements UserDetailsService{
	
	/**@author ShinSungjin
	 * Security에 사용 될 CustomLoginService입니다. 
	 * */
	
	private UserDAO userDao;
	
	public CustomLoginService(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws LoginException {
		//여기서 username = userId
		log.info("LoginService, loadUserByUsername =="+ username);
		//사욪아 정보를 불러옵니다
		Optional<UserDTO> userInfo = Optional.of(userDao.getByUserId(username)); 
		log.info("optinal Test  ==== {}", userInfo.get());
		//정보 조회가 제대로 되지 않을 시, 존재하지 않는 회원이라는 error message를 보냅니다.
		if(userInfo.isEmpty()) {
			throw new LoginException(LoginExceptionEnum.LOGIN_NOTFOUND_MEMBER);
		}
		UserDTO user = userInfo.get();
		log.info("user DTO Login Service {} ",user.toString());
		//user가 ben되어 있는지 확인합니다.
		if(user.isLoked()) {
			throw new AuthenticationCredentialsNotFoundException("정지된 사용자입니다. 관리자에게 문의하세요");
		}
		//로그인 한 사용자의 권한에 맞게 authentication을 부여합니다. 
		List<GrantedAuthority> auth = new ArrayList<>();
		try {			
			if(user.getUserrole().equals(User_Role.user.getValue())) {
				log.info("authorize User");
				auth.add(new SimpleGrantedAuthority(User_Role.user.getValue()));	
						}
			else if(user.getUserrole().equals(User_Role.bis.getValue())) {
				auth.add(new SimpleGrantedAuthority(User_Role.bis.getValue()));
						}
			else if(user.getUserrole().equals(User_Role.admin.getValue())) {
				auth.add(new SimpleGrantedAuthority(User_Role.admin.getValue()));
			}
		
		}catch (NullPointerException e) {
			throw new LoginException(LoginExceptionEnum.LOGIN_NOTFOUND_MEMBER);
		}
		catch(AuthenticationCredentialsNotFoundException e){
			throw new LoginException(LoginExceptionEnum.LOGIN_NOT_AUTHENTICATION);
		}
		catch (LoginException e) {
			log.info("tryCatch {}", e.getClass());
		}
		catch (Exception e) {
			log.info("tryCatch {}", e.getClass());
			e.printStackTrace();
		}		
		//UserDetails의 객체를 만들어 보냅니다.
		UserDetails userIN  =new User(user.getUserId(), user.getPassword(), auth);
		log.info("userIN {}", userIN);
		return userIN;
			
	}

	
}
