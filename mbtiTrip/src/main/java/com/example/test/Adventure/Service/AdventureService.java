package com.example.test.Adventure.Service;

import org.springframework.data.domain.Page;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

public interface AdventureService {

	public Page<AdventureDTO> getList(int page, String kw, String categoryName);
	 
	 public AdventureDTO getPost(Integer userid);
	 
	 public AdventureDTO create(String title, String content, String admin);
	 
	 public AdventureDTO modify(AdventureDTO adDto, String title, String content);
	 
	 public void delete(AdventureDTO adDto);
	 
	 public AdventureDTO vote(AdventureDTO adDto, UserDTO user);
}
