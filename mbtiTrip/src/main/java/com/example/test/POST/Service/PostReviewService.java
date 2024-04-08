package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

public interface PostReviewService {

	public PostReviewDTO getPost(Integer itemID);
	 
	 public int create(String title, String content, UserDTO user, Post_CategoryDTO category);
	 
	 public int modify(PostReviewDTO postDto, String title, String content);
	 
	 public void delete(PostReviewDTO postDto);
	 
	 public int suggestion(PostReviewDTO postDto, UserDTO userDto);
	 
	 List<PostReviewDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
