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

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.Service.DataNotFoundException;
import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DAO.ReplaceDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;

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
public class ReplaceServiceImpl implements ReplaceService{

	@Autowired
	ReplaceDAO rpDAO;

	private Specification<ReplaceDTO> search(String kw, String categoryName) {
        return new Specification<ReplaceDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<ReplaceDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<ReplaceDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<ReplaceDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<ReplaceDTO, ReplaceCategoryDTO> c = q.join("category", JoinType.LEFT);
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
	public Page<ReplaceDTO> getList(int page, String kw, String categoryName) {
		 List<Sort.Order> sorts = new ArrayList<>();
	     sorts.add(Sort.Order.desc("createDate"));
	     Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	     Specification<ReplaceDTO> spec = search(kw, categoryName);
	     return this.rpDAO.findAll(spec,pageable);
	}

	@Override
	public ReplaceDTO getPost(Integer userid) {
		 Optional<ReplaceDTO> rp = this.rpDAO.findById(userid);
		 if (rp.isPresent()) {
	        	ReplaceDTO adr1 = rp.get();        	
	        	adr1.setViews(adr1.getViews()+1);        	
	        	this.rpDAO.save(adr1);
	            	return adr1;
	        } else {
	            throw new DataNotFoundException("adventure_Review not found");
	        }	
		  }
	

	@Override
	public ReplaceDTO create(String title, String content, String admin) {
		ReplaceDTO adDto = new ReplaceDTO();
        adDto.setReplaceName(title);
        adDto.setReplaceContents(content);
        adDto.setUpdateDate(LocalDateTime.now());
        adDto.setReplaceAdmin(admin);
		return adDto;
	}

	@Override
	public ReplaceDTO modify(ReplaceDTO rpDto, String title, String content) {
		rpDto.setReplaceName(title);
        rpDto.setReplaceContents(content);
        rpDto.setModifyDate(LocalDateTime.now());
        
        
        return this.rpDAO.save(rpDto);
	}

	@Override
	public void delete(ReplaceDTO rpDto) {
		this.rpDAO.delete(rpDto);
		
	}

	@Override
	public ReplaceDTO vote(ReplaceDTO rpDto, UserDTO user) {
		rpDto.getVoter().add(user);
        
        return this.rpDAO.save(rpDto);
	}
	
	// 게시물을 조회하고 조회수 증가
	@Transactional
	public ReplaceDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
			Cookie oldCookie = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			for (Cookie cookie : cookies)
			if (cookie.getName().equals("replace"))
					     oldCookie = cookie;

			if (oldCookie != null) {
				if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
					 rpDAO.updateCount(id);
					 oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
					 oldCookie.setPath("/");
					 oldCookie.setMaxAge(60 * 60 * 24);
					 response.addCookie(oldCookie);
			}
			}
			else {
					rpDAO.updateCount(id);
					Cookie newCookie = new Cookie("replaceView","[" + id + "]");
				    newCookie.setPath("/");
					newCookie.setMaxAge(60 * 60 * 24);
					response.addCookie(newCookie);
					    }

					    return rpDAO.findById(id).orElseThrow(() -> {
					        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
					    });
					}
	
	
	
}
