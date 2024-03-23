package com.example.test.replace.Service;

import org.springframework.data.domain.Page;
import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;

public interface ReplaceService {

	public Page<ReplaceCategoryDTO> getList(int page, String kw, String categoryName);
	 
	 public ReplaceCategoryDTO getPost(Integer userid);
	 
	 public ReplaceCategoryDTO create(String title, String content, String admin);
	 
	 public ReplaceCategoryDTO modify(ReplaceCategoryDTO adDto, String title, String content);
	 
	 public void delete(ReplaceCategoryDTO adDto);
	 
	 public ReplaceCategoryDTO vote(ReplaceCategoryDTO adDto, UserDTO user);
}
