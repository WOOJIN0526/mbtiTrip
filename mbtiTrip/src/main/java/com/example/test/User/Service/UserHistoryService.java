package com.example.test.User.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface UserHistoryService {

	public List<HashMap<String, Object>> uxReplace(String userName);
	
	public List<HashMap<String, Object>> uxAdventure(String userName);
	
	public List<HashMap<String, Object>> uxRutin(String userName);

	public List<HashMap<String, Object>> selectUserPost(Principal principal);

	public List<HashMap<String, Object>> selectUserQnA(Principal principal);

	public void userViewItem(ItemDTO itemDTO, Principal principal);
	public void userViewPost(PostDTO PostDTO, Principal principal);
}
