package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class UserHistoryDAO {

	@Autowired
	SqlSessionTemplate sqlsessiontemplate;
	
	public void  viewCkRE(UserHistoryDTO userHistory) {
		this.sqlsessiontemplate.insert("view.viewRE", userHistory);
	}
	
	public void viewCkAD(UserHistoryDTO userHistoryDTO) {
		this.sqlsessiontemplate.insert("view.viewAD", userHistoryDTO);
	}
	
	public void viewCkPO(UserHistoryDTO userHistoryDTO) {
		this.sqlsessiontemplate.insert("view.viewPO", userHistoryDTO);
	}
	
	public List<ReplaceDTO> viewReturnRE(String userName) {
		log.info("HIStoryDAO => {}", userName);
		List<ReplaceDTO> returnvalue =this.sqlsessiontemplate.selectList("view.ReturnRE", userName);
		return returnvalue;
	}
	
	public List<AdventureDTO> viewReturnAD(String userName) {
		log.info("HIStoryDAO => {}", userName);
		return this.sqlsessiontemplate.selectList("view.ReturnAD", userName);
	}
	
	public List<PostDTO> viewReturnPO(String userName) {
		log.info("HIStoryDAO => {}", userName);
		return this.sqlsessiontemplate.selectList("view.ReturnPO", userName);
	}

	public List<HashMap<String, Object>> uxMbti(String userName){
		log.info("UserName --->{}", userName);
		return this.sqlsessiontemplate.selectList("view.userLikeMbti", userName);
	}
	
	public List<HashMap<String, Object>> rutin(String mbti){
		return this.sqlsessiontemplate.selectList("view.rutin", mbti);
	}
	
	  
}
