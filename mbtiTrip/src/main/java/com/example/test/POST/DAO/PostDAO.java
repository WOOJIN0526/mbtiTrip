package com.example.test.POST.DAO;


import java.awt.print.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.Post;
import com.example.test.POST.DTO.PostDTO;

@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

	 PostDTO findBySubject(String title) {
		return null;
	}
	 PostDTO findBySubjectAndContent(String title, String content) {
		return null;
	}
	 List<PostDTO> findBySubjectLike(String title) {
		return null;
	}

	public Optional<Post> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(PostDTO postDto) {
		// TODO Auto-generated method stub
		
	}

	public void delete(PostDTO postDTO) {
		// TODO Auto-generated method stub
		
	}

	public Page<PostDTO> findAll(Specification<Post> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
}
