package com.example.test.POST.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;


public interface PostService {

	 
		
	 
		 public PostDTO getPost(Integer postID);
		 
		 public int create(String title, String content, UserDTO user, Post_CategoryDTO category);
		 
		 public int modify(PostDTO postDto, String title, String content);
		 
		 public void delete(PostDTO postDto);
		 
		 public int suggestion(PostDTO postDto, UserDTO userDto);
		 
		 List<PostDTO> list(Criteria cri) throws Exception;

		 public int listCount(Criteria cri) throws Exception;
}
