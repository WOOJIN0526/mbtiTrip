package com.example.test.User.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.example.test.User.DTO.AdminDTO;

public interface AdminService {

	public boolean userBaned(String username); 
	public boolean userUnblock(String username);
	//오늘 방문자 수 
	public int rating(String userName);
	
	//총 사용자 수 
	public Map<String, Integer> userCnt();
	
	public int liveUserCnt();
	

	//각각의 detail 
	@Bean
	public void dailyrating();
	

	public List<HashMap<String, Object>> mbtiCnt();
	
	public List<HashMap<String, Object>> userList();
	
	public List<HashMap<String, Object>> bisList();
}
