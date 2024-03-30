package com.example.test.Adventure.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.POST.DTO.Criteria;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;

public interface AdventureService {

	 public AdventureDTO getAdventure(Integer userid);
	 
	 public AdventureDTO create(String title, String content, UserDTO user, Adventure_CategoryDTO category);
	 
	 public AdventureDTO modify(AdventureDTO adDto, String title, String content);
	 
	 public void delete(AdventureDTO adDto);
	 
	 public AdventureDTO vote(AdventureDTO adDto, UserDTO userDto);
	 
	 List<AdventureDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
