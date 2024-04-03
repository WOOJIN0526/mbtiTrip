package com.example.test.User.Service;

import java.security.Principal;
import java.util.Map;

import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;

public interface UserService {
		
	public int createUser(UserDTO userdto) ; //생성 안되면 , 0 , 1

	public void updateUser(Map<String, Object> user);  //게시판 수정과 내용 동일   //회원정보 수정 
	
	public String findByUserName(UserDAO user);  //닉네임 중복 방지 용도, 회원 아이디 찾기

	public Map<String, Object> login(UserDTO userdto);

	int userUpdate(UserDTO userdto, Principal principal);

	public Map<String, Object> getInfo(Integer uID);

	public int createBis(UserDTO userdto);

	public int BisUpdate(UserDTO userdto);

	public Integer findByUID(String userName);

	public UserDTO getUser(String name);


	
}
