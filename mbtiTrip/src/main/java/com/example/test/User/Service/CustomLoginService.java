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

import groovy.cli.Option;
import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class CustomLoginService implements UserDetailsService{
	
	
	
	private UserDAO userDao;
	
	public CustomLoginService( UserDAO userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//여기서 username = userId
		try {
		log.info("LoginService, loadUserByUsername =="+ username);
		Optional<UserDTO> userInfo = Optional.of(userDao.getByUserId(username)); 
		log.info("optinal Test  ==== {}", userInfo.get());
		if(userInfo.isEmpty()) {
			log.info("THROWS ===", "UsernameNotFoundException");
			throw new UsernameNotFoundException("찾을 수 없는 유저입니다.");
		}

		log.info("message === {}" , userInfo);
	
		UserDTO user = userInfo.get();
		log.info("user DTO Login Service {} ",user.toString());
		if(user.isLoked()) {
			log.info("ㅋㅋㅋ정지됐노 ㅋㅋㅋ");
			throw new AuthenticationCredentialsNotFoundException("정지된 사용자입니다. 관리자에게 문의하세요");
		}
		else {
			List<GrantedAuthority> auth = new ArrayList<>();
						
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
			UserDetails userIN  =new User(user.getUserId(), user.getPassword(), auth);
			log.info("userIN {}", userIN);
			return userIN;
		}
		
		} catch (Exception e) {
			log.info("tryCatch {}", e.getClass());
		}		
	return null;
			
	}

	
}
