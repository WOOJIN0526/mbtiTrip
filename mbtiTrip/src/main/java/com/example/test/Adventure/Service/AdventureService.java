package com.example.test.Adventure.Service;

import java.security.Principal;
import java.util.List;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.AdminDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;

public interface AdventureService {

	 public List<ItemDTO> getList(Criteria criteria);
	 
	 public int getTotal(Criteria criteria);

	 public ItemDTO getPost(Integer itemid);
	 public ItemDTO getPost(Integer itemID, Principal principal);
	 
	 public int create(ItemType Type, Integer mbti, UserDTO Username,Integer price, 
			 String itemName,String location, String tel, String contents, String[] ImgeUrl);
	 
	 public int modify(ItemDTO itemdto,ItemType Type, Integer mbti, Integer price, 
			 String itemName,String location, String tel, String contents, String[] ImgeUrl );
	 
	 public void delete(ItemDTO itemDto);
	 
	 public int suggestion(ItemDTO itemDto, UserDTO user);
	 

	 
}
