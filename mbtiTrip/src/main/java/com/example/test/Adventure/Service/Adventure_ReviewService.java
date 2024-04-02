package com.example.test.Adventure.Service;

import java.util.List;

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

public interface Adventure_ReviewService {

	 public Adventure_ReviewDTO getPost(Integer adventureReviewID);
	 
	 public Adventure_ReviewDTO create(String reviewTitle, String content, UserDTO user, Adventure_CategoryDTO category);
	 
	 public Adventure_ReviewDTO modify(Adventure_ReviewDTO adrDto, String reviewTitle, String content);
	 
	 public void delete(Adventure_ReviewDTO adrDto);
	 
	 public Adventure_ReviewDTO suggestion(Adventure_ReviewDTO adrDto, UserDTO user);
	 
	 List<Adventure_ReviewDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
	 
	
}
