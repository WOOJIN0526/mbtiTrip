package com.example.test.User.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

@Service 
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public int createUser(UserDTO userDTO) {
		int result = this.userDao.insert(userDTO);
			return result;
	
	}

	@Override
	public void updateUser(Map<String, Object> user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer findByUserID(UserDAO user) {
		// TODO Auto-generated method stub
		return null;
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

}
