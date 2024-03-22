package com.example.test.Adventure.Service;

import org.springframework.data.domain.Page;

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.User.DTO.UserDTO;

public interface Adventure_ReviewService {

	public Page<Adventure_ReviewDTO> getList(int page, String kw, String categoryName);
	 
	 public Adventure_ReviewDTO getPost(Integer userid);
	 
	 public Adventure_ReviewDTO create(String title, String content, UserDTO user);
	 
	 public Adventure_ReviewDTO modify(Adventure_ReviewDTO adrDto, String title, String content);
	 
	 public void delete(Adventure_ReviewDTO adrDto);
	 
	 public Adventure_ReviewDTO vote(Adventure_ReviewDTO adrDto, UserDTO userDto);
}
