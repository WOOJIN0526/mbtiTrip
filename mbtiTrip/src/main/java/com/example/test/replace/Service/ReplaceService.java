package com.example.test.replace.Service;

import org.springframework.data.domain.Page;
import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface ReplaceService {

	public Page<ReplaceDTO> getList(int page, String kw, String categoryName);
	 
	 public ReplaceDTO getPost(Integer userid);
	 
	 public ReplaceDTO create(String title, String content, String admin);
	 
	 public ReplaceDTO modify(ReplaceDTO rpDto, String title, String content);
	 
	 public void delete(ReplaceDTO rpDto);
	 
	 public ReplaceDTO vote(ReplaceDTO rpDto, UserDTO user);
}
