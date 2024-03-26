package com.example.test.Adventure.Service;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.AdventureDAO.AdventureDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;




@Service
public class AdventureServiceImpl implements AdventureService{

	@Autowired
	AdventureDAO adDAO;
	
		private Specification<AdventureDTO> search(String kw, String categoryName) {
        return new Specification<AdventureDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<AdventureDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<AdventureDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<AdventureDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<AdventureDTO, Adventure_CategoryDTO> c = q.join("category", JoinType.LEFT);
                Join<AnswerDTO, UserDTO> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목 
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용 
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자 
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("username"), "%" + kw + "%"),   // 답변 작성자 
                		cb.like(c.get("name"), "%" + categoryName + "%"));	// 카테고리 이름
                		
            }
        };
    }

		@Override
		public Page<AdventureDTO> getList(int page, String kw, String categoryName) {
			 List<Sort.Order> sorts = new ArrayList<>();
		     sorts.add(Sort.Order.desc("createDate"));
		     Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		     Specification<AdventureDTO> spec = search(kw, categoryName);
		     return this.adDAO.findAll(spec,pageable);
		}

		@Override
		public AdventureDTO getPost(Integer userid) {
			 Optional<AdventureDTO> ad = this.adDAO.findById(userid);
			 if (ad.isPresent()) {
		        	AdventureDTO ad1 = ad.get();        	
		        	ad1.setViews(ad1.getViews()+1);        	
		        	this.adDAO.save(ad1);
		            	return ad1;
		        } else {
		            throw new DataNotFoundException("adventure not found");
		        }	
			  }

		

		@Override
		public AdventureDTO create(String title, String content, String admin) {
			AdventureDTO adDto = new AdventureDTO();
	        adDto.setAdventureName(title);
	        adDto.setAdventureContent(content);
	        adDto.setUpdateDate(LocalDateTime.now());
	        adDto.setAdventureAdmin(admin);
			return adDto;
		}

		@Override
		public AdventureDTO modify(AdventureDTO adDto, String title, String content) {
			adDto.setAdventureName(title);
	        adDto.setAdventureContent(content);
	        adDto.setModifyDate(LocalDateTime.now());
	        
	        
	        return this.adDAO.save(adDto);
		}

		@Override
		public void delete(AdventureDTO adDto) {
			this.adDAO.delete(adDto);
			
		}

		@Override
		public AdventureDTO vote(AdventureDTO adDto, UserDTO user) {
			adDto.getVoter().add(user);
	        
	        return this.adDAO.save(adDto);
		}
	
	
		// 게시물을 조회하고 조회수 증가
		@Transactional
		public AdventureDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
			 Cookie oldCookie = null;
			    Cookie[] cookies = request.getCookies();
			    if (cookies != null)
			        for (Cookie cookie : cookies)
			            if (cookie.getName().equals("postView"))
			                oldCookie = cookie;

			    if (oldCookie != null) {
			        if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
			            adDAO.updateCount(id);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    }
			    else {
			        adDAO.updateCount(id);
			        Cookie newCookie = new Cookie("adventureView","[" + id + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
			    }

			    return adDAO.findById(id).orElseThrow(() -> {
			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			    });
			}
	

	
}
