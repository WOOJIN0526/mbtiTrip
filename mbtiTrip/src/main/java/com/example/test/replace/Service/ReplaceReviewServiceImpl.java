package com.example.test.replace.Service;

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

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DAO.ReplaceReviewDAO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

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
public class ReplaceReviewServiceImpl implements ReplaceReviewService{

	@Autowired
	ReplaceReviewDAO rprDAO;
	
	private Specification<ReplaceReviewDTO> search(String kw, String categoryName) {
        return new Specification<ReplaceReviewDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<ReplaceReviewDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<ReplaceReviewDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<ReplaceReviewDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<ReplaceReviewDTO, Adventure_CategoryDTO> c = q.join("category", JoinType.LEFT);
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
	public Page<ReplaceReviewDTO> getList(int page, String kw, String categoryName) {
		 List<Sort.Order> sorts = new ArrayList<>();
	     sorts.add(Sort.Order.desc("createDate"));
	     Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	     Specification<ReplaceReviewDTO> spec = search(kw, categoryName);
	     return this.rprDAO.findAll(spec,pageable);
	}

	@Override
	public ReplaceReviewDTO getPost(Integer userid) {
		Optional<ReplaceReviewDTO> rpr = this.rprDAO.findById(userid);
		 if (rpr.isPresent()) {
	        	ReplaceReviewDTO rpr1 = rpr.get();        	
	        	rpr1.setViews(rpr1.getViews()+1);        	
	        	this.rprDAO.save(rpr1);
	            	return rpr1;
	        } else {
	            throw new DataNotFoundException("replace_Review not found");
	        }	
		  }
	

	@Override
	public ReplaceReviewDTO create(String title, String content, String user) {
		ReplaceReviewDTO adrDto = new ReplaceReviewDTO();
        adrDto.setReviewTitle(title);
        adrDto.setContent(content);
        adrDto.setUpdateDate(LocalDateTime.now());
        adrDto.setUserName(user);
        
        
        return this.rprDAO.save(adrDto);
	}

	@Override
	public ReplaceReviewDTO modify(ReplaceReviewDTO rprDto, String title, String content) {
		rprDto.setReviewTitle(title);
        rprDto.setContent(content);
        rprDto.setModifyDate(LocalDateTime.now());
        
        
        return this.rprDAO.save(rprDto);
	}

	@Override
	public void delete(ReplaceReviewDTO rprDto) {
		this.rprDAO.delete(rprDto);
		
	}
	
	// 게시물을 조회하고 조회수 증가
		@Transactional
		public ReplaceReviewDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
				Cookie oldCookie = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null)
				for (Cookie cookie : cookies)
				if (cookie.getName().equals("replace"))
						     oldCookie = cookie;

				if (oldCookie != null) {
					if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
						 rprDAO.updateCount(id);
						 oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
						 oldCookie.setPath("/");
						 oldCookie.setMaxAge(60 * 60 * 24);
						 response.addCookie(oldCookie);
				}
				}
				else {
						rprDAO.updateCount(id);
						Cookie newCookie = new Cookie("replaceView","[" + id + "]");
					    newCookie.setPath("/");
						newCookie.setMaxAge(60 * 60 * 24);
						response.addCookie(newCookie);
						    }

						    return rprDAO.findById(id).orElseThrow(() -> {
						        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
						    });
						}

}
