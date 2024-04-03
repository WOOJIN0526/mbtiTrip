package com.example.test.Adventure.Service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.AdventureDAO.Adventure_ReviewDAO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;



@Service
public class Adventure_ReviewServiceImpl implements Adventure_ReviewService {

	@Autowired
	Adventure_ReviewDAO adrDAO;

	@Override
	public Adventure_ReviewDTO getPost(Integer adventureReviewID) {
		 Optional<Adventure_ReviewDTO> adventureR = this.adrDAO.findById(adventureReviewID);
		  if (adventureR.isPresent()) {
	        	Adventure_ReviewDTO adrDto = adventureR.get();        	
	        	adrDto.setViews(adrDto.getViews()+1);        	
	        	this.adrDAO.save(adrDto);
	            	return adrDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}

	@Override
	public Adventure_ReviewDTO create(String reviewTitle, String content, UserDTO user,
			Adventure_CategoryDTO category) {
		Adventure_ReviewDTO adrDto = new Adventure_ReviewDTO();
		adrDto.setReviewTitle(reviewTitle);
		adrDto.setContent(content);
		adrDto.setUser(user);
		adrDto.setAdventure_categoryID(category);
		return this.adrDAO.save(adrDto);
	}

	@Override
	public Adventure_ReviewDTO modify(Adventure_ReviewDTO adrDto, String reviewTitle, String content) {
		adrDto.setReviewTitle(reviewTitle);
		adrDto.setContent(content);
		
		 return this.adrDAO.save(adrDto);
	}

	@Override
	public void delete(Adventure_ReviewDTO adrDto) {
		this.adrDAO.delete(adrDto);
	}

	@Override
	public Adventure_ReviewDTO suggestion(Adventure_ReviewDTO adrDto, UserDTO user) {
		adrDto.getSuggestion().add(user);
        
        return this.adrDAO.save(adrDto);
	}

	@Override
	public List<Adventure_ReviewDTO> list(Criteria cri) throws Exception {
		return adrDAO.list(cri);
	}

	@Override
	public int listCount(Criteria cri) throws Exception {
		return adrDAO.listCount(cri);
	}

	// 게시물을 조회하고 조회수 증가
		@Transactional
		public Adventure_ReviewDTO detail(Integer adventureReviewID, HttpServletRequest request, HttpServletResponse response) {
			 Cookie oldCookie = null;
			    Cookie[] cookies = request.getCookies();
			    if (cookies != null)
			        for (Cookie cookie : cookies)
			            if (cookie.getName().equals("AdventureReviewView"))
			                oldCookie = cookie;

			    if (oldCookie != null) {
			        if (!oldCookie.getValue().contains("[" + adventureReviewID.toString() + "]")) {
			            adrDAO.updateCount(adventureReviewID);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + adventureReviewID + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    }
			    else {
			        adrDAO.updateCount(adventureReviewID);
			        Cookie newCookie = new Cookie("AdventureReviewView","[" + adventureReviewID + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
			    }

			    return adrDAO.findById(adventureReviewID).orElseThrow(() -> {
			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			    });
			}
	
	
	
}
