package com.example.test.Adventure.Service;



//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Service;
//
//import com.example.test.Adventure.DTO.AdventureDTO;
//import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
//
//import com.example.test.POST.DTO.AnswerDTO;
//
//import com.example.test.POST.DTO.PostDTO;
//import com.example.test.POST.Service.DataNotFoundException;
//import com.example.test.User.DTO.UserDTO;
//
//import jakarta.persistence.criteria.CriteriaBuilder;
//import jakarta.persistence.criteria.CriteriaQuery;
//import jakarta.persistence.criteria.Join;
//import jakarta.persistence.criteria.JoinType;
//import jakarta.persistence.criteria.Predicate;
//import jakarta.persistence.criteria.Root;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.transaction.Transactional;




//@Service
//public class AdventureServiceImpl implements AdventureService{
//
//	@Autowired
//	AdventureDAO adDAO;
//	
//	
//	
//	@Override
//	public AdventureDTO getAdventure(Integer userid) {
//			Optional<AdventureDTO> ADdto = this.adDAO.findById(userid);
//			  if (ADdto.isPresent()) {
//		        	AdventureDTO post = ADdto.get();        	
//		        	post.setViews(post.getViews()+1);        	
//		        	this.adDAO.save(post);
//		            	return post;
//		        } else {
//		            throw new DataNotFoundException("adventure not found");
//		        }	
//			  }
//	
//
//
//
//	@Override
//	public AdventureDTO create(String title, String content, UserDTO user, Adventure_CategoryDTO category) {
//		AdventureDTO adDto = new AdventureDTO();
//        adDto.setAdventureName(title);
//        adDto.setAdventureContent(content);
//        adDto.setAdCategory(category);
//        adDto.setUpdateDate(LocalDateTime.now());
//        adDto.setAuthor(user);
//        
//        
//        return this.adDAO.save2(adDto);
//	}
//
//
//
//	@Override
//	public AdventureDTO modify(AdventureDTO adDto, String title, String content) {
//		adDto.setAdventureName(title);
//        adDto.setAdventureContent(content);
//        adDto.setModifyDate(LocalDateTime.now());
//        
//        
//        return this.adDAO.save2(adDto);
//	}
//
//
//
//	@Override
//	public void delete(AdventureDTO adDto) {
//		 this.adDAO.delete(adDto);
//		
//	}
//
//
//
//	@Override
//	public AdventureDTO vote(AdventureDTO adDto, UserDTO userDto) {
//		adDto.getVoter().add(userDto);
//        
//        return this.adDAO.save2(adDto);
//	}
//
//
//
//	@Override
//	public List<AdventureDTO> list(Criteria cri) throws Exception {
//		// TODO Auto-generated method stub
//				return adDAO.list(cri);
//	}
//
//
//
//	@Override
//	public int listCount(Criteria cri) throws Exception {
//		// TODO Auto-generated method stub
//				return adDAO.listCount(cri);
//
//	}
//
//
//
//		// 게시물을 조회하고 조회수 증가
//		@Transactional
//		public AdventureDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
//			 Cookie oldCookie = null;
//			    Cookie[] cookies = request.getCookies();
//			    if (cookies != null)
//			        for (Cookie cookie : cookies)
//			            if (cookie.getName().equals("postView"))
//			                oldCookie = cookie;
//
//			    if (oldCookie != null) {
//			        if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
//			            adDAO.updateCount(id);
//			            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
//			            oldCookie.setPath("/");
//			            oldCookie.setMaxAge(60 * 60 * 24);
//			            response.addCookie(oldCookie);
//			        }
//			    }
//			    else {
//			        adDAO.updateCount(id);
//			        Cookie newCookie = new Cookie("adventureView","[" + id + "]");
//			        newCookie.setPath("/");
//			        newCookie.setMaxAge(60 * 60 * 24);
//			        response.addCookie(newCookie);
//			    }
//
//			    return adDAO.findById(id).orElseThrow(() -> {
//			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
//			    });
//			}
//	수정

	
//}
