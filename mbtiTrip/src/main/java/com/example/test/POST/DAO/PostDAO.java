package com.example.test.POST.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.PostDTO;

@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

	public void savePost(PostDTO postDTO) {
		
		
	}

	public void updatePost(PostDTO postDTO) {
		
		
	}

	public void deletePost(Integer postId) {
		
		
	}

	public PostDTO getPostById(Integer postId) {
		
		return null;
	}

	public List<PostDTO> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	};
	
	public List<PostDTO> searchPostsByTitle(String title) {
		return null;
	}
	
}
