package com.example.test.replace.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DAO.ItemDAO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DAO.ReplaceDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;



//@Service
//public class ReplaceServiceImpl implements ReplaceService{
//
//	@Autowired
//	ItemDAO itemDAO;
//
//	@Override
//	public ItemDTO getPost(Integer itemid) {
//		Optional<ItemDTO> replace = this.itemDAO.findById(itemid);
//		  if (replace.isPresent()) {
//	        	ItemDTO itemDto = replace.get();        	
//	        	itemDto.setUpratring(itemDto.getUpratring()+1);        	
//	        	this.itemDAO.save(itemDto);
//	            	return itemDto;
//	        } else {
//	            throw new DataNotFoundException("question not found");
//	        }	
//	}
//
//	@Override
//	public int create(Integer mbtiID, Integer cityID, Integer replaceTypeId,
//			String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel,
//			UserDTO userName) {
//		ItemDTO item = new ItemDTO();
//		item.setContents(replaceContent);
//		item.setImgeUrl(null);
//		item.setLocation(replaceLocation);
//		item.setMbti(mbtiID);
//		item.setPrice(replacePrice);
//		item.setTel(tel);
//		item.itemDAO.get
//		
//		rp.setPostCategoryID(postCategoryID);
//		rp.setMbtiID(mbtiID);
//		rp.setCityID(cityID);
//		rp.setReplaceType(replaceTypeId);
//		rp.setReplaceLocation(replaceLocation);
//		rp.setReplaceName(replaceName);
//		rp.setReplacePrice(replacePrice);
//		rp.setReplaceContents(replaceContent);
//		rp.setTel(tel);
//		rp.setReplaceAdmin(replaceAdmin.getUsername());
//		rp.setReplaceCategoryID(replace_CategoryID.getReplace_Category());
//		return this.rpDAO.save(rp);
//	}
//
//	@Override
//	public ReplaceDTO modify(ReplaceDTO rpDto, Integer postCategoryID, Integer mbtiID, Integer cityID,
//			Integer replaceTypeId, String replaceLocation, String replaceName, Integer replacePrice,
//			String replaceContent, String tel) {
//		rpDto.setPostCategoryID(postCategoryID);
//		rpDto.setMbtiID(mbtiID);
//		rpDto.setCityID(cityID);
//		rpDto.setReplaceType(replaceTypeId);
//		rpDto.setReplaceLocation(replaceLocation);
//		rpDto.setReplaceName(replaceName);
//		rpDto.setReplacePrice(replacePrice);
//		rpDto.setReplaceContents(replaceContent);
//		rpDto.setTel(tel);
//		
//		return this.rpDAO.save(rpDto);
//	}
//
//	@Override
//	public void delete(ReplaceDTO rpDto) {
//		this.rpDAO.delete(rpDto);
//		
//	}
//
//	@Override
//	public ReplaceDTO suggestion(ReplaceDTO rpDto, UserDTO user) {
//		rpDto.getSuggestion().add(user);
//        
//        return this.rpDAO.save(rpDto);
//	}
//
//	@Override
//	public List<ReplaceDTO> list(Criteria cri) throws Exception {
//		// TODO Auto-generated method stub
//		return rpDAO.list(cri);
//	}
//
//	@Override
//	public int listCount(Criteria cri) throws Exception {
//		// TODO Auto-generated method stub
//		return rpDAO.listCount(cri);
//	}
//
//			// 게시물을 조회하고 조회수 증가
//			@Transactional
//			public ReplaceDTO detail(Integer replaceID, HttpServletRequest request, HttpServletResponse response) {
//				 Cookie oldCookie = null;
//				    Cookie[] cookies = request.getCookies();
//				    if (cookies != null)
//				        for (Cookie cookie : cookies)
//				            if (cookie.getName().equals("ReplaceView"))
//				                oldCookie = cookie;
//
//				    if (oldCookie != null) {
//				        if (!oldCookie.getValue().contains("[" + replaceID.toString() + "]")) {
//				            itemDAO.updateCount(replaceID);
//				            oldCookie.setValue(oldCookie.getValue() + "_[" + replaceID + "]");
//				            oldCookie.setPath("/");
//				            oldCookie.setMaxAge(60 * 60 * 24);
//				            response.addCookie(oldCookie);
//				        }
//				    }
//				    else {
//				        itemDAO.updateCount(replaceID);
//				        Cookie newCookie = new Cookie("ReplaceView","[" + replaceID + "]");
//				        newCookie.setPath("/");
//				        newCookie.setMaxAge(60 * 60 * 24);
//				        response.addCookie(newCookie);
//				    }
//
//				    return itemDAO.findById(replaceID).orElseThrow(() -> {
//				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
//				    });
//				}
//
//	
//	
//	
//	
//}
