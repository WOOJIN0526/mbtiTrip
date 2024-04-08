package com.example.test.replace.Service;

import java.util.List;

import com.example.test.User.DTO.AdminDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.ItemType;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;


public interface ReplaceService {

	 public ItemDTO getPost(Integer itemID);
	 
	 public int create(ItemType Type, Integer mbti, AdminDTO Username,Integer price, 
			 String itemName,String location, String tel, String contents, String[] ImgeUrl);
	 
	 public int modify(ItemDTO itemdto,ItemType Type, Integer mbti, Integer price, 
			 String itemName,String location, String tel, String contents, String[] ImgeUrl );
	 
	 public void delete(ItemDTO itemDto);
	 
	 public int suggestion(ItemDTO itemDto, UserDTO user);
	 
	 List<ItemDTO> list(Criteria cri) throws Exception;//검색 조건에 따라 게시글 목록을 가져오는 역할

	 public int listCount(Criteria cri) throws Exception;//검색 조건에 부합하는 게시글의 총 수를 반환
	 
	 public void setRating(Integer itemID);
}
