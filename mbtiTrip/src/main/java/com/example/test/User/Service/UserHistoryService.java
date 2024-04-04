package com.example.test.User.Service;

import java.util.HashMap;
import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface UserHistoryService {

	public List<ReplaceDTO> uxReplace(String userName);
	
	public List<AdventureDTO> uxAdventure(String userName);
	
	public List<HashMap<String, Object>> uxRutin(String userName);

}
