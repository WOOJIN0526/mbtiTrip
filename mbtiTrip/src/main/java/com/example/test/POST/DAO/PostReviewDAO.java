package com.example.test.POST.DAO;

import java.util.List;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.paging.Criteria;

@Repository
public class PostReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public Optional<PostReviewDTO> findById(Integer itemID) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("postReview.findById", itemID);
	}

	public int create(PostReviewDTO postReview) {
		return this.sqlSessiontemplate.insert("postReview.create",postReview);
		// TODO Auto-generated method stub
		
	}

	public void delete(PostReviewDTO postDto) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.delete("postReview.delete", postDto);
	}

	

	public void updateCount(Integer itemID) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("postReview.updateCount", itemID);
	}

	public List<PostReviewDTO> getList(Criteria criteria) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("postReview.getList", criteria);
	}

	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("postReview.getTotal", cri);
	}

	public int update(PostReviewDTO pr) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.update("postReview.update", pr);
	}

}
