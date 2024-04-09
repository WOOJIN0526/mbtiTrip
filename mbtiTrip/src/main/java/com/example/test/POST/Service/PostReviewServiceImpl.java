package com.example.test.POST.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostReviewDAO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class PostReviewServiceImpl implements PostReviewService{

	@Autowired
	PostReviewDAO prDAO;

	@Override
	public List<PostReviewDTO> getList(Criteria criteria) {
		// TODO Auto-generated method stub
		return prDAO.getList(criteria);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return prDAO.getTotal(cri);
	}
	
	@Override
	public PostReviewDTO getPost(Integer itemID) {
		 Optional<PostReviewDTO> post = this.prDAO.findById(itemID);
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
	public int create(String title, String content, UserDTO user, Post_CategoryDTO category) {
		
		PostReviewDTO pr = new PostReviewDTO();
		pr.setTitle(title);
		pr.setContent(content);
		pr.setWriter(user);
		pr.setCategory(category);
		pr.setUpdateDate(LocalDateTime.now());
		
		return this.prDAO.create(pr);
	}

	@Override
	public int modify(PostReviewDTO pr, String title, String content) {
		pr.setTitle(title);
		pr.setContent(content);
		pr.setModifyDate(LocalDateTime.now());
		
		return this.prDAO.update(pr);
	}

	@Override
	public void delete(PostReviewDTO postDto) {
		// TODO Auto-generated method stub
		this.prDAO.delete(postDto);
	}

	@Override
	public int suggestion(PostReviewDTO postDto, UserDTO userDto) {
		postDto.getSuggestion().add(userDto);
        
        return this.prDAO.create(postDto);
	}

	
	
	// 게시물을 조회하고 조회수 증가
			@Transactional
			public PostReviewDTO detail(Integer itemID, HttpServletRequest request, HttpServletResponse response) {
				 Cookie oldCookie = null;
				    Cookie[] cookies = request.getCookies();
				    if (cookies != null)
				        for (Cookie cookie : cookies)
				            if (cookie.getName().equals("postView"))
				                oldCookie = cookie;

				    if (oldCookie != null) {
				        if (!oldCookie.getValue().contains("[" + itemID.toString() + "]")) {
				            prDAO.updateCount(itemID);
				            oldCookie.setValue(oldCookie.getValue() + "_[" + itemID + "]");
				            oldCookie.setPath("/");
				            oldCookie.setMaxAge(60 * 60 * 24);
				            response.addCookie(oldCookie);
				        }
				    }
				    else {
				        prDAO.updateCount(itemID);
				        Cookie newCookie = new Cookie("postView","[" + itemID + "]");
				        newCookie.setPath("/");
				        newCookie.setMaxAge(60 * 60 * 24);
				        response.addCookie(newCookie);
				    }

				    return prDAO.findById(itemID).orElseThrow(() -> {
				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				    });
				}
	
}
