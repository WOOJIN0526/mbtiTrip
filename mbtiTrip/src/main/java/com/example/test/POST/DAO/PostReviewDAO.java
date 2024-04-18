package com.example.test.POST.DAO;

import java.util.List;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;
import com.example.test.paging.PaginationVo;

@Repository
public class PostReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	


	
	public void create(PostReviewDTO post) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.insert("postReview.create", post);
	}


	public Optional<PostReviewDTO> findById(Integer postReviewID) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("postReview.findById", postReviewID);
	}


	public void update(PostReviewDTO post) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("postReview.update", post);
	}



	public void delete(Integer postReviewId) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.delete("postReview.delete", postReviewId);
	}





	public List<PostReviewDTO> findByPostCategoryID(Long postCategoryID) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("postReview.findByPostCategoryId", postCategoryID);
	}

	public void replyRegister(AnswerDTO reply) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.insert("postReview.replyRegister", reply);
	}



	public List<AnswerDTO> replyList(PostReviewDTO postReviewId) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("postReview.replyList", postReviewId);
	}



	public void replyModify(AnswerDTO reply) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("postReview.replyModify", reply);
	}


	public void replyRemove(Integer answerId) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.delete("postReview.replyRemove", answerId);
	}
	
	
	
	public Double getRatingAverage(int itemID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("postReview.getRatingAverage", itemID);
	}

	public void updateRating(PostReviewDTO pr) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("postReview.updateRating", pr);
	}


	public List<PostReviewDTO> getListPage(Page pagination) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("postReview.getListPage", pagination);
	}


	public int getCount() {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("postReview.getCount");
	}


	public List<PostReviewDTO> search(Page page) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("postReview.search", page);
	}


















	

}
