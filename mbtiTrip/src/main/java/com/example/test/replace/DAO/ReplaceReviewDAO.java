package com.example.test.replace.DAO;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;


@Repository
public class ReplaceReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> replaceCategory) {
		int result = this.sqlSessiontemplate.insert("replaceCategory.insert",replaceCategory);
		return result;
	}

	public Optional<ReplaceReviewDTO> findById(Integer replaceReviewID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("replaceReview.findById", replaceReviewID);
	}

	public ReplaceReviewDTO save(ReplaceReviewDTO rprDto) {
		return rprDto;
		// TODO Auto-generated method stub
		
	}

	public void delete(ReplaceReviewDTO rprDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("replaceReview.delete", rprDto);
	}

	public List<ReplaceReviewDTO> list(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("replaceReview.list", cri);
	}

	public int listCount(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("replaceReview.listCount", cri);
	}

	public void updateCount(Integer replaceReviewID) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("replaceReview.updateCount", replaceReviewID);
	}

	
	
	
	

}
