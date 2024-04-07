package com.example.test.replace.Service;

import java.util.List;


import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;


public interface ReplaceService {

	public ItemDTO getPost(Integer itemid);
	 
	 public int create(Integer mbtiID, Integer cityID, Integer replaceTypeId, 
			 String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel, UserDTO userName);
	 
	 public int modify(ItemDTO itemDto, Integer mbtiID, Integer cityID, 
			 Integer replaceTypeId, String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel);
	 
	 public void delete(ItemDTO itemDto);
	 
	 public int suggestion(ItemDTO rpDto, UserDTO user);
	 
	 List<ItemDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
