package com.example.test.POST.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;


public interface PostService {

	 
		 public List<PostDTO> getList(Criteria criteria);
		 
		 public int getTotal(Criteria cri);
	 
		 public PostDTO getPost(Integer postID);
		 
		 public int create(String title, String content, UserDTO user, Post_CategoryDTO category);
		 
		 public int modify(PostDTO postDto, String title, String content);
		 
		 public void delete(PostDTO postDto);
		 
		 public int suggestion(PostDTO postDto, UserDTO userDto);
		 
		

		public List<PostDTO> findPostByCategoryID(Long postCategoryID);

		
		
}
