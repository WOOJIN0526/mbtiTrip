package com.example.test.POST.DAO;



import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.Post;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostPage;




@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

	public Page<PostDTO> findAll(Specification<PostDTO> spec, org.springframework.data.domain.Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<PostDTO> findById(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public PostDTO save(PostDTO postDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(PostDTO postDto) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

	
	
}
