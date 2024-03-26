package com.example.test.replace.DAO;


import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


import com.example.test.replace.DTO.ReplaceReviewDTO;


@Repository
public class ReplaceReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> replaceCategory) {
		int result = this.sqlSessiontemplate.insert("replaceCategory.insert",replaceCategory);
		return result;
	}

	public Page<ReplaceReviewDTO> findAll(Specification<ReplaceReviewDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ReplaceReviewDTO> findById(Integer userid) {
		Optional<ReplaceReviewDTO> result = sqlSessiontemplate.selectOne("replaceReview.findById", userid);
		return result;
	}

	public ReplaceReviewDTO save(ReplaceReviewDTO rprDto) {
		ReplaceReviewDTO result = sqlSessiontemplate.selectOne("replaceReview.save", rprDto);
		return result;
	}

	public int delete(ReplaceReviewDTO rprDto) {
		int result = sqlSessiontemplate.delete("replaceReview.delete", rprDto);
		return result;
		
	}

	public int updateCount(Integer id) {
		int result = sqlSessiontemplate.update("replaceReview.update", id);
		return result;
		
	}
	
	
	

}
