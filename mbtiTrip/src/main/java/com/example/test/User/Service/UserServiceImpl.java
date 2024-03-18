package com.example.test.User.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.UserDAO;

@Service 
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserDAO userDao;
	
	@Override
	public int createUser(Map<String, Object> user) {
			int result = this.userDao.insert(user);
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

}
