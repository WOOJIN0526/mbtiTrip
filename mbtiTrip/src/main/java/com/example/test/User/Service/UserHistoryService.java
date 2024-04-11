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
	/*사용자 활동 기반 추천 기능*/
	public List<HashMap<String, Object>> uxReplace(String userName);
	
	public List<HashMap<String, Object>> uxAdventure(String userName);
	
	public List<HashMap<String, Object>> uxRutin(String userName);

	public List<HashMap<String, Object>> selectUserPost(Principal principal);

	public List<HashMap<String, Object>> selectUserQnA(Principal principal);

	
	/*사용자 활동 기반 추적 기능*/
	public void userViewItem(ItemDTO itemDTO, Principal principal);
	
	public void userViewPost(PostDTO PostDTO, Principal principal);
	
	public void ViewCreateItem();
	public void ViewCreatePost();

	public List<HashMap<String, Object>> viewRating(Principal principal);

	/*사용자가 최근에 본 유형별 아이템 상위 5개*/
	public List<List<?>> userViewInfo(Principal principal);
}
