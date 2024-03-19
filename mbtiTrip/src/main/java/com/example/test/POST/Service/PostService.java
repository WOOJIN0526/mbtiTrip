package com.example.test.POST.Service;

import java.util.List;


import com.example.test.POST.DTO.PostDTO;

public interface PostService {

	
	void createPost(PostDTO postDTO);
    
	void updatePost(PostDTO postDTO);
    
	void deletePost(Integer postId);
    
	PostDTO getPostById(Integer postId);
    
	List<PostDTO> getAllPosts(); 
	
	List<PostDTO> searchPostsByTitle(String title);
	
}
