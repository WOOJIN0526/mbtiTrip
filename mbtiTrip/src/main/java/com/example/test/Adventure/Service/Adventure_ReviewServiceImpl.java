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
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.AdventureDAO.Adventure_ReviewDAO;
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
public class Adventure_ReviewServiceImpl implements Adventure_ReviewService {

	@Autowired
	Adventure_ReviewDAO adrDAO;
	
	private Specification<Adventure_ReviewDTO> search(String kw, String categoryName) {
        return new Specification<Adventure_ReviewDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<Adventure_ReviewDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<Adventure_ReviewDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<Adventure_ReviewDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<Adventure_ReviewDTO, Adventure_CategoryDTO> c = q.join("category", JoinType.LEFT);
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
	public Page<Adventure_ReviewDTO> getList(int page, String kw, String categoryName) {
		List<Sort.Order> sorts = new ArrayList<>();
	     sorts.add(Sort.Order.desc("createDate"));
	     Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	     Specification<Adventure_ReviewDTO> spec = search(kw, categoryName);
	     return this.adrDAO.findAll(spec,pageable);
	}

	@Override
	public Adventure_ReviewDTO getPost(Integer reviewid) {
		Optional<Adventure_ReviewDTO> adr = this.adrDAO.findById(reviewid);
		if (adr.isPresent()) {
        	Adventure_ReviewDTO adr1 = adr.get();        	
        	adr1.setViews(adr1.getViews()+1);        	
        	this.adrDAO.save(adr1);
            	return adr1;
        } else {
            throw new DataNotFoundException("adventure_Review not found");
        }	
	  }
	

	@Override
	public Adventure_ReviewDTO create(String title, String content, String user) {
		Adventure_ReviewDTO adrDto = new Adventure_ReviewDTO();
        adrDto.setReviewTitle(title);
        adrDto.setContent(content);
        adrDto.setUpdateDate(LocalDateTime.now());
        adrDto.setUserName(user);
        
        
        return this.adrDAO.save(adrDto);
	}

	@Override
	public Adventure_ReviewDTO modify(Adventure_ReviewDTO adrDto, String title, String content) {
		adrDto.setReviewTitle(title);
        adrDto.setContent(content);
        adrDto.setModifyDate(LocalDateTime.now());
        
        
        return this.adrDAO.save(adrDto);
	}

	@Override
	public void delete(Adventure_ReviewDTO adrDto) {
		this.adrDAO.delete(adrDto);
		
	}

	// 게시물을 조회하고 조회수 증가
			@Transactional
			public Adventure_ReviewDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
				 Cookie oldCookie = null;
				    Cookie[] cookies = request.getCookies();
				    if (cookies != null)
				        for (Cookie cookie : cookies)
				            if (cookie.getName().equals("adventureView"))
				                oldCookie = cookie;

				    if (oldCookie != null) {
				        if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
				            adrDAO.updateCount(id);
				            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
				            oldCookie.setPath("/");
				            oldCookie.setMaxAge(60 * 60 * 24);
				            response.addCookie(oldCookie);
				        }
				    }
				    else {
				        adrDAO.updateCount(id);
				        Cookie newCookie = new Cookie("adventureReviewView","[" + id + "]");
				        newCookie.setPath("/");
				        newCookie.setMaxAge(60 * 60 * 24);
				        response.addCookie(newCookie);
				    }

				    return adrDAO.findById(id).orElseThrow(() -> {
				        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
				    });
				}

	
}
