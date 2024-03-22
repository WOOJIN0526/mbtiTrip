package com.example.test.POST.Service;

import org.springframework.data.domain.Page;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

public interface PostService {

	 public Page<PostDTO> getList(int page, String kw, String categoryName);
	 
	 public PostDTO getPost(Integer userid);
	 
	 public PostDTO create(String title, String content, UserDTO user);
	 
	 public PostDTO modify(PostDTO postDto, String title, String content);
	 
	 public void delete(PostDTO postDto);
	 
	 public PostDTO vote(PostDTO postDto, UserDTO userDto);
	 
	 
}
