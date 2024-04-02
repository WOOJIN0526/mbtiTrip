package com.example.test.Adventure.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.AdventureDAO.AdventureDAO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Service
public class AdventureServiceImpl implements AdventureService{

	@Autowired
	AdventureDAO adDAO;

	@Override
	public AdventureDTO getPost(Integer adventureID) {
		 Optional<AdventureDTO> adventure = this.adDAO.findById(adventureID);
		  if (adventure.isPresent()) {
	        	AdventureDTO adDto = adventure.get();        	
	        	adDto.setViews(adDto.getViews()+1);        	
	        	this.adDAO.save(adDto);
	            	return adDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}

	@Override
	public AdventureDTO create(Integer postCategoryID, Integer mbtiID, Integer cityID, Integer adventureTypeId, 
			 String adventureLocation, String adventureName, Integer adventurePrice, String adventureContent, String tel, UserDTO adventureAdmin, Adventure_CategoryDTO adventure_CategoryID) {
		AdventureDTO adDto = new AdventureDTO();
		adDto.setPostCategoryID(postCategoryID);
		adDto.setAdventureCategoryID(adventure_CategoryID);
		adDto.setMbtiID(mbtiID);
		adDto.setCityID(cityID);
		adDto.setAdventureTypeId(adventureTypeId);
		adDto.setAdventureLocation(adventureLocation);
        adDto.setAdventureName(adventureName);
        adDto.setAdventurePrice(adventurePrice);
        adDto.setAdventureContent(adventureContent);
        adDto.setTel(tel);
        adDto.setAdventureAdmin(adventureAdmin);
        adDto.setUpdateDate(LocalDateTime.now());
       
        
        
        return this.adDAO.save(adDto);
	}

	@Override
	public AdventureDTO modify(AdventureDTO adDto, Integer postCategoryID,  Integer mbtiID, Integer cityID, 
			 Integer adventureTypeId, String adventureLocation, String adventureName, Integer adventurePrice, String adventureContent, String tel) {
		adDto.setPostCategoryID(postCategoryID);
		adDto.setMbtiID(mbtiID);
		adDto.setCityID(cityID);
		adDto.setAdventureTypeId(adventureTypeId);
		adDto.setAdventureLocation(adventureLocation);
        adDto.setAdventureName(adventureName);
        adDto.setAdventurePrice(adventurePrice);
        adDto.setAdventureContent(adventureContent);
        adDto.setTel(tel);
        adDto.setModifyDate(LocalDateTime.now());
        
        
        
        return this.adDAO.save(adDto);
	}

	@Override
	public void delete(AdventureDTO adDto) {
		this.adDAO.delete(adDto);
		
	}

	@Override
	public AdventureDTO suggestion(AdventureDTO adDto, UserDTO user) {
		adDto.getSuggestion().add(user);
        
        return this.adDAO.save(adDto);
	}

	@Override
	public List<AdventureDTO> list(Criteria cri) throws Exception {
		return adDAO.list(cri);
	}

	@Override
	public int listCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return adDAO.listCount(cri);
	}

	// 게시물을 조회하고 조회수 증가
		@Transactional
		public AdventureDTO detail(Integer adventureID, HttpServletRequest request, HttpServletResponse response) {
			 Cookie oldCookie = null;
			    Cookie[] cookies = request.getCookies();
			    if (cookies != null)
			        for (Cookie cookie : cookies)
			            if (cookie.getName().equals("postView"))
			                oldCookie = cookie;

			    if (oldCookie != null) {
			        if (!oldCookie.getValue().contains("[" + adventureID.toString() + "]")) {
			            adDAO.updateCount(adventureID);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + adventureID + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    }
			    else {
			        adDAO.updateCount(adventureID);
			        Cookie newCookie = new Cookie("boardView","[" + adventureID + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
			    }

			    return adDAO.findById(adventureID).orElseThrow(() -> {
			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			    });
			}
	
	
}
