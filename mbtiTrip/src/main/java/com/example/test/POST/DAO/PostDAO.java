package com.example.test.POST.DAO;





import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

	PostDTO findBySubject(String subject) {
		return sqlSessiontemplate.selectOne("post.findBySubject", subject);
	}
    PostDTO findBySubjectAndContent(String subject, String content) {
		return null;
	}
    List<PostDTO> findBySubjectLike(String subject) {
		return sqlSessiontemplate.selectOne("findBySubjectLike", subject);
	}
    
   

	public PostDTO save(PostDTO postDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(PostDTO postDto) {
		sqlSessiontemplate.delete("post.delete", postDto);
		
	}
	
	@Modifying
	@Query("update Board b set b.count = b.count + 1 where b.id = :id")
	public void updateCount(Integer id) {
		sqlSessiontemplate.update("post.updateCount", id);
	}

	public Optional<PostDTO> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	Page<PostDTO> findAll(Pageable pageable) {
		return null;
	}
	public Page<PostDTO> findAll(Specification<PostDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	
	
}
