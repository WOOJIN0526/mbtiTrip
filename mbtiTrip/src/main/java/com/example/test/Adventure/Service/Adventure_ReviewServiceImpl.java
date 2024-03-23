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

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.AdventureDAO.Adventure_ReviewDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
        return adr.get();
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

	

	
}
