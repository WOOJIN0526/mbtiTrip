package com.example.test.User.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.extern.log4j.Log4j2;


@Log4j2
@Service
public class UserHistroyServiceImpl implements UserHistoryService{

	@Autowired
	UserHistoryDAO userhistoryDAO;
	
	@Override
	public List<HashMap<String,Object>> uxReplace(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		log.info("mbti 값 고정 되어 있음 변경 필요 ");
		String testMbti = "ISTJ";
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinREByUx(testMbti);
		return adRutin;
	}

	@Override
	public List<HashMap<String,Object>> uxAdventure(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		String largestMbti = (String) ULM.get(0).get("mbti");
		log.info("mbti 값 고정 되어 있음 변경 필요 ");
		String testMbti = "ISTJ";
		List<HashMap<String,Object>> adRutin = userhistoryDAO.rutinADByUx(testMbti);
		return adRutin;
	}
	
	@Override
	public List<HashMap<String, Object>> uxRutin(String userName) {
		List<HashMap<String, Object>> ULM = userhistoryDAO.uxMbti(userName);
		//가장 많이 검색 된 상위 3개의 mbti가 저장된 리스트 
		String largestMbti = (String) ULM.get(0).get("mbti");
		
		log.info("아래 rutinbyUX MBTI 값 수정해야 됨");
		List<HashMap<String, Object>> rutin = userhistoryDAO.rutinByUx("ISTJ");		
		return rutin;
	}

	@Override
	public List<HashMap<String, Object>> selectUserPost(Principal principal) {
		String userName = principal.getName();
		List<HashMap<String, Object>> userPost = userhistoryDAO.userCreatePost(userName);
		return userPost;
	}

	@Override
	public List<HashMap<String, Object>> selectUserQnA(Principal principal) {
		String userName = principal.getName();
		List<HashMap<String, Object>> userQnA = userhistoryDAO.userCreateQnA(userName);
		return userQnA;
	}

}
