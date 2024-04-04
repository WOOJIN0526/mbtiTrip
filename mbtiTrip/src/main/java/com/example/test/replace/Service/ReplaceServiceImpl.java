package com.example.test.replace.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DAO.ReplaceDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;



@Service
public class ReplaceServiceImpl implements ReplaceService{

	@Autowired
	ReplaceDAO rpDAO;

	@Override
	public ReplaceDTO getPost(Integer replaceID) {
		Optional<ReplaceDTO> replace = this.rpDAO.findById(replaceID);
		  if (replace.isPresent()) {
	        	ReplaceDTO rpDto = replace.get();        	
	        	rpDto.setViews(rpDto.getViews()+1);        	
	        	this.rpDAO.save(rpDto);
	            	return rpDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}

	@Override
	public ReplaceDTO create(Integer postCategoryID, Integer mbtiID, Integer cityID, Integer replaceTypeId,
			String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel,
			UserDTO replaceAdmin, ReplaceCategoryDTO replace_CategoryID) {
		ReplaceDTO rp = new ReplaceDTO();
		rp.setPostCategoryID(postCategoryID);
		rp.setMbtiID(mbtiID);
		rp.setCityID(cityID);
		rp.setReplaceType(replaceTypeId);
		rp.setReplaceLocation(replaceLocation);
		rp.setReplaceName(replaceName);
		rp.setReplacePrice(replacePrice);
		rp.setReplaceContents(replaceContent);
		rp.setTel(tel);
		rp.setReplaceAdmin(replaceAdmin);
		rp.setReplaceCategoryID(replace_CategoryID);
		return this.rpDAO.save(rp);
	}

	@Override
	public ReplaceDTO modify(ReplaceDTO rpDto, Integer postCategoryID, Integer mbtiID, Integer cityID,
			Integer replaceTypeId, String replaceLocation, String replaceName, Integer replacePrice,
			String replaceContent, String tel) {
		rpDto.setPostCategoryID(postCategoryID);
		rpDto.setMbtiID(mbtiID);
		rpDto.setCityID(cityID);
		rpDto.setReplaceType(replaceTypeId);
		rpDto.setReplaceLocation(replaceLocation);
		rpDto.setReplaceName(replaceName);
		rpDto.setReplacePrice(replacePrice);
		rpDto.setReplaceContents(replaceContent);
		rpDto.setTel(tel);
		
		return this.rpDAO.save(rpDto);
	}

	@Override
	public void delete(ReplaceDTO rpDto) {
		this.rpDAO.delete(rpDto);
		
	}

	@Override
	public ReplaceDTO suggestion(ReplaceDTO rpDto, UserDTO user) {
		rpDto.getSuggestion().add(user);
        
        return this.rpDAO.save(rpDto);
	}

	@Override
	public List<ReplaceDTO> list(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return rpDAO.list(cri);
	}

	@Override
	public int listCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return rpDAO.listCount(cri);
	}

			// 게시물을 조회하고 조회수 증가
			@Transactional
			public ReplaceDTO detail(Integer replaceID, HttpServletRequest request, HttpServletResponse response) {
				 Cookie oldCookie = null;
				    Cookie[] cookies = request.getCookies();
				    if (cookies != null)
				        for (Cookie cookie : cookies)
				            if (cookie.getName().equals("ReplaceView"))
				                oldCookie = cookie;

				    if (oldCookie != null) {
				        if (!oldCookie.getValue().contains("[" + replaceID.toString() + "]")) {
				            rpDAO.updateCount(replaceID);
				            oldCookie.setValue(oldCookie.getValue() + "_[" + replaceID + "]");
				            oldCookie.setPath("/");
				            oldCookie.setMaxAge(60 * 60 * 24);
				            response.addCookie(oldCookie);
				        }
				    }
				    else {
				        rpDAO.updateCount(replaceID);
				        Cookie newCookie = new Cookie("ReplaceView","[" + replaceID + "]");
				        newCookie.setPath("/");
				        newCookie.setMaxAge(60 * 60 * 24);
				        response.addCookie(newCookie);
				    }

				    return rpDAO.findById(replaceID).orElseThrow(() -> {
				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				    });
				}

	
	
	
	
}
