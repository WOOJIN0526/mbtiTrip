package com.example.test.User.Service;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service 
public class UserServiceImpl implements UserService{

	private BCryptPasswordEncoder bcrypasswordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public int createUser(UserDTO userDTO) {
		String userPassword = userDTO.getPassword();
		log.info("userPassword : {}", userPassword);
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		userDTO.setPassword(encodePassword);
		int result = this.userDao.insert(userDTO);
			return result;
	
	}
	
	@Override
	public int createBis(UserDTO userdto) {
		int result = this.userDao.insertBis(userdto);
		return result;
	}

	@Override
	public void updateUser(Map<String, Object> user) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String findByUserName(UserDAO user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public  Map<String, Object> login(UserDTO userdto) {
		return userDao.login(userdto);
	}

	@Override
	public int userUpdate(UserDTO userdto) {
		int result =userDao.userUpdate(userdto);
		return result;
		
	}
	
	@Override
	public int BisUpdate(UserDTO userdto) {
		int result =userDao.BisUpdate(userdto);
		return result;
	}

	@Override
	public Map<String, Object> getInfo(Integer uID) {
		// TODO Auto-generated method stub
		return userDao.getInfo(uID);
	}

	@Override
	public Integer findByUID(String userName) {
		log.info("User Service {}", userName);
		Integer UID = userDao.getUID(userName);
		log.info("User Service after {}", userName);
		return UID;
	}

	@Override
	public UserDTO getUser(String name) {
		UserDTO siteUser = this.userDao.findByUsername(name);
		return siteUser;
	}

	public Integer princeUID(Principal principal) {
		String userName = principal.getName();
		Integer UID = findByUID(userName);
		return UID;
	}


}
