package com.example.test.Adventure.Service;

import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

public interface AdventureService {


	 public AdventureDTO getPost(Integer adventureID);
	 
	 public AdventureDTO create(Integer postCategoryID, Integer mbtiID, Integer cityID, Integer adventureTypeId, 
			 String adventureLocation, String adventureName, Integer adventurePrice, String adventureContent, String tel, String adventureAdmin, String adventure_CategoryID);
	 
	 public AdventureDTO modify(AdventureDTO adDto, Integer postCategoryID,  Integer mbtiID, Integer cityID, 
			 Integer adventureTypeId, String adventureLocation, String adventureName, Integer adventurePrice, String adventureContent, String tel);
	 
	 public void delete(AdventureDTO adDto);
	 
	 public AdventureDTO suggestion(AdventureDTO adDto, UserDTO user);
	 
	 List<AdventureDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
