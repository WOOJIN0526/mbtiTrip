package com.example.test.POST.Service;




import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;




@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	UserHistoryService userHistoryService;



	@Override
	public List<PostDTO> getList(Criteria criteria) {
		// TODO Auto-generated method stub
		return postDAO.getList(criteria);
	}



	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return postDAO.getTotal(cri);
	}



	//해당게시글 가져옴, 조회수 증가
	@Override
	public PostDTO getPost(Integer userid) {
		 Optional<PostDTO> post = this.postDAO.findById(userid);
		  if (post.isPresent()) {
	        	PostDTO postDto = post.get();        	
	        	postDto.setViews(postDto.getViews()+1);        	
	        	this.postDAO.save(postDto);
	            	return postDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}
	
	//생성
	@Override
	public int create(String title, String content, UserDTO user, Post_CategoryDTO category) {
		PostDTO postDto = new PostDTO();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setPost_category(category);
        postDto.setUpdateDate(LocalDateTime.now());
        postDto.setWriter(user);
        

         
        return this.postDAO.save(postDto);
	}

	//수정
	@Override
	public int modify(PostDTO postDto, String title, String content) {
		postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setModifyDate(LocalDateTime.now());
        
        
        return this.postDAO.update(postDto);
	}

	//삭제
	@Override
	public void delete(PostDTO postDto) {
		 this.postDAO.delete(postDto);
		
		
	}

	//추천
	@Override
	public int suggestion(PostDTO postDto, UserDTO userDto) {
		postDto.getSuggestion().add(userDto);
        
        return this.postDAO.save(postDto);
	}


	
	// 게시물을 조회하고 조회수 증가
		@Transactional
		public PostDTO detail(Integer postID, HttpServletRequest request, HttpServletResponse response
				, Principal principal) {
			 Cookie oldCookie = null;
			    Cookie[] cookies = request.getCookies();
			    if (cookies != null)
			        for (Cookie cookie : cookies)
			            if (cookie.getName().equals("postView"))
			                oldCookie = cookie;

			    if (oldCookie != null) {
			        if (!oldCookie.getValue().contains("[" + postID.toString() + "]")) {
			            postDAO.updateCount(postID);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + postID + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    }
			    else {
			        postDAO.updateCount(postID);
			        Cookie newCookie = new Cookie("postView","[" + postID + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
			    }
			    PostDTO userCount = new PostDTO();
			    userCount.setPostID(postID);
			    userHistoryService.userViewPost(userCount, principal);
			    
			    return postDAO.findById(postID).orElseThrow(() -> {
			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			    });
			}

		@Override
		public List<PostDTO> findPostByCategoryID(Long postCategoryID) {
			// 게시글 목록 조회
	        List<PostDTO> post = postDAO.findByPostCategoryID(postCategoryID);
			return post;
	        
	    }





	

	
	





	
	
    

}
