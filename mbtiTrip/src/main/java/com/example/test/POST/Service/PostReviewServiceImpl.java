package com.example.test.POST.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostReviewDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class PostReviewServiceImpl implements PostReviewService{

	@Autowired
	PostReviewDAO prDAO;

	
	
	@Override
	public List<PostReviewDTO> list(Page page) throws Exception {
		// TODO Auto-generated method stub
		return this.prDAO.list(page);
	}


	@Override
	public void create(PostReviewDTO post) throws Exception {
		// TODO Auto-generated method stub
		
		setRating(post.getItemID());
		this.prDAO.create(post);
	}

	@Override
	public PostReviewDTO getPost(Integer postReviewID) {
		 Optional<PostReviewDTO> post = this.prDAO.findById(postReviewID);
		  if (post.isPresent()) {
	        	PostReviewDTO postReview = post.get();        	
	        	postReview.setViews(postReview.getViews()+1);        	
	        	this.prDAO.create(postReview);
	            	return postReview;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
		  }




	@Override
	public void modify(PostReviewDTO post) throws Exception {
		// TODO Auto-generated method stub
		setRating(post.getItemID());
		this.prDAO.update(post);
	}


	@Override
	public void remove(Integer postReviewId) throws Exception {
		// TODO Auto-generated method stub
		PostReviewDTO post = new PostReviewDTO();
		
		setRating(post.getItemID());
		this.prDAO.delete(postReviewId);
	}


	@Override
	public List<PostReviewDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return this.prDAO.search(keyword);
	}


	@Override
	public List<PostReviewDTO> search(Page page) throws Exception {
		// TODO Auto-generated method stub
		return this.prDAO.search(page);
	}


	@Override
	public Integer totalCount() throws Exception {
		// TODO Auto-generated method stub
		return this.prDAO.totalCount();
	}

	
	@Override
	public void suggestion(PostReviewDTO post, UserDTO user) throws Exception {
		// TODO Auto-generated method stub
		post.getSuggestion().add(user);
        
        this.prDAO.create(post);
	}


	@Override
	public List<PostReviewDTO> findPostByCategoryID(Long postCategoryID) {
		// TODO Auto-generated method stub
		List<PostReviewDTO> post = prDAO.findByPostCategoryID(postCategoryID);
		return post;
	}


	@Override
	public void replyRegister(AnswerDTO reply) throws Exception {
		// TODO Auto-generated method stub
		this.prDAO.replyRegister(reply);
	}


	@Override
	public List<AnswerDTO> replyList(PostReviewDTO postReviewId) throws Exception {
		// TODO Auto-generated method stub
		return this.prDAO.replyList(postReviewId);
	}


	@Override
	public void replyModify(AnswerDTO reply) throws Exception {
		// TODO Auto-generated method stub
		this.prDAO.replyModify(reply);
	}


	@Override
	public void replyRemove(Integer answerId) throws Exception {
		// TODO Auto-generated method stub
		this.prDAO.replyRemove(answerId);
	}

	//평점
	@Override
	public void setRating(int itemID) {
	// TODO Auto-generated method stub
	Double ratingAvg = prDAO.getRatingAverage(itemID);
	if(ratingAvg == null) {
	   ratingAvg = 0.0;
	}
				
	ratingAvg = (double) (Math.round(ratingAvg*10));
	ratingAvg = ratingAvg / 10;
				
	PostReviewDTO pr = new PostReviewDTO();
	pr.setItemID(itemID);
	pr.setRatingAvg(ratingAvg);
				
	prDAO.updateRating(pr);
	}
	
}
