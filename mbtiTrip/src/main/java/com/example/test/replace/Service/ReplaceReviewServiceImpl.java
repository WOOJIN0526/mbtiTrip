package com.example.test.replace.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DAO.ReplaceReviewDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;



@Service
public class ReplaceReviewServiceImpl implements ReplaceReviewService{

	@Autowired
	ReplaceReviewDAO rprDAO;

	@Override
	public ReplaceReviewDTO getPost(Integer replaceReviewID) {
		 Optional<ReplaceReviewDTO> RR = this.rprDAO.findById(replaceReviewID);
		  if (RR.isPresent()) {
	        	ReplaceReviewDTO rprDto = RR.get();        	
	        	rprDto.setViews(rprDto.getViews()+1);        	
	        	this.rprDAO.save(rprDto);
	            	return rprDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}

	@Override
	public ReplaceReviewDTO create(String reviewTitle, String content, UserDTO user, ReplaceCategoryDTO category) {
		ReplaceReviewDTO rprDTO= new ReplaceReviewDTO();
		rprDTO.setReviewTitle(reviewTitle);
		rprDTO.setContent(content);
		rprDTO.setUserId(user);
		rprDTO.setCategory(category);
		return this.rprDAO.save(rprDTO);
	}

	@Override
	public ReplaceReviewDTO modify(ReplaceReviewDTO rprDto, String reviewTitle, String content) {
		rprDto.setReviewTitle(reviewTitle);
		rprDto.setContent(content);
		return this.rprDAO.save(rprDto);
	}

	@Override
	public void delete(ReplaceReviewDTO rprDto) {
		this.rprDAO.delete(rprDto);
		
	}

	@Override
	public ReplaceReviewDTO suggestion(ReplaceReviewDTO rprDto, UserDTO user) {
		rprDto.getSuggestion().add(user);
        
        return this.rprDAO.save(rprDto);
	}

	@Override
	public List<ReplaceReviewDTO> list(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return rprDAO.list(cri);
	}

	@Override
	public int listCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return rprDAO.listCount(cri);
	}

			// 게시물을 조회하고 조회수 증가
			@Transactional
			public ReplaceReviewDTO detail(Integer replaceReviewID, HttpServletRequest request, HttpServletResponse response) {
				 Cookie oldCookie = null;
				    Cookie[] cookies = request.getCookies();
				    if (cookies != null)
				        for (Cookie cookie : cookies)
				            if (cookie.getName().equals("postView"))
				                oldCookie = cookie;

				    if (oldCookie != null) {
				        if (!oldCookie.getValue().contains("[" + replaceReviewID.toString() + "]")) {
				            rprDAO.updateCount(replaceReviewID);
				            oldCookie.setValue(oldCookie.getValue() + "_[" + replaceReviewID + "]");
				            oldCookie.setPath("/");
				            oldCookie.setMaxAge(60 * 60 * 24);
				            response.addCookie(oldCookie);
				        }
				    }
				    else {
				        rprDAO.updateCount(replaceReviewID);
				        Cookie newCookie = new Cookie("boardView","[" + replaceReviewID + "]");
				        newCookie.setPath("/");
				        newCookie.setMaxAge(60 * 60 * 24);
				        response.addCookie(newCookie);
				    }

				    return rprDAO.findById(replaceReviewID).orElseThrow(() -> {
				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				    });
				}
		



}
