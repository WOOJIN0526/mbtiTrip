package com.example.test.POST.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.PostDTO;

@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;

	@Override
    public void createPost(PostDTO postDTO) {
        postDAO.savePost(postDTO);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        postDAO.updatePost(postDTO);
    }

    @Override
    public void deletePost(Integer postId) {
        postDAO.deletePost(postId);
    }

    @Override
    public PostDTO getPostById(Integer postId) {
        return postDAO.getPostById(postId);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postDAO.getAllPosts();
    }

    @Override
    public List<PostDTO> searchPostsByTitle(String title) {
        return postDAO.searchPostsByTitle(title);
    }
	
   
    
}
