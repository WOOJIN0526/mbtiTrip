package com.example.test.User.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.replace.DTO.ReplaceDTO;

@Service
public class UserHistroyServiceImpl implements UserHistoryService{

	@Autowired
	UserHistoryDAO userhistoryDAO;
	
	@Override
	public List<HashMap<String,Object>> uxReplace(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinREByUx(largestMbti);
		return adRutin;
	}

	@Override
	public List<HashMap<String,Object>> uxAdventure(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinADByUx(largestMbti);
		return adRutin;
	}
	
	@Override
	public List<HashMap<String, Object>> uxRutin(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		//가장 많이 검색 된 상위 3개의 mbti가 저장된 리스트 
		String largestMbti = (String) ULM.get(0).get("mbti");
		List<HashMap<String, Object>> rutin = userhistoryDAO.rutinByUx(largestMbti);		
		return rutin;
	}

}
