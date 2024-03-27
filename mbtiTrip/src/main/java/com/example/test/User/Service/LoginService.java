package com.example.test.User.Service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

import groovy.cli.Option;
import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class LoginService implements UserDetailsService{
	
	private UserDAO userDao;
	
	public LoginService( UserDAO userDao) {
		this.userDao = userDao;
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserDTO> userInfo = userDao.findByUserID(username);
		log.info(userInfo.toString());
		if(userInfo.isPresent()) {
			UserDTO userIn = userInfo.get();
			
			UserDTO user = UserDTO.builder()
					.UID(userIn.getUID())
					.userId(userIn.getUserId())
					.userName(userIn.getUsername())
					.mbti(userIn.getMbti())
					.BNum(userIn.getBNum())
					.password(userIn.getPassword())
					.phone(userIn.getPhone())
					.Rank(userIn.getRank())
					.mail(userIn.getMail())
					.cart(userIn.getCart())
					.history(userIn.getHistory())
					.userrole(userIn.getUserrole())
					.build();
			
			log.info("user: {}", user);
			return user;
		}
		return null;
	}

}
