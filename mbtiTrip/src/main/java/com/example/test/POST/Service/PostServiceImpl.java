package com.example.test.POST.Service;


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

import com.example.test.POST.DAO.PostDAO;
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
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	private Specification<PostDTO> search(String kw) {
        return new Specification<PostDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<PostDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<PostDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<PostDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<AnswerDTO, UserDTO> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목 
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용 
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자 
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자 
            }
        };
    }
	
	@Override
	public Page<PostDTO> getList(int page, String kw) {
		 List<Sort.Order> sorts = new ArrayList<>();
	        sorts.add(Sort.Order.desc("createDate"));
	        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	        Specification<PostDTO> spec = search(kw);
	        return this.postDAO.findAll(spec,pageable);
	}

	@Override
	public PostDTO getPost(Integer userid) {
		  Optional<PostDTO> post = this.postDAO.findById(userid);
	        return post.get();
	}

	@Override
	public PostDTO create(String title, String content, UserDTO user) {
		 	PostDTO postDto = new PostDTO();
	        postDto.setTitle(title);
	        postDto.setContent(content);
	        postDto.setUpdateDate(LocalDateTime.now());
	        postDto.setAuthor(user);
	        
	        
	        return this.postDAO.save(postDto);
	}

	@Override
	public PostDTO modify(PostDTO postDto, String title, String content) {
		 	postDto.setTitle(title);
	        postDto.setContent(content);
	        postDto.setModifyDate(LocalDateTime.now());
	        
	        
	        return this.postDAO.save(postDto);
	}

	@Override
	public void delete(PostDTO postDto) {
		 this.postDAO.delete(postDto);
		
	}

	@Override
	public PostDTO vote(PostDTO postDto, UserDTO userDto) {
			postDto.getVoter().add(userDto);
	        
	        return this.postDAO.save(postDto);
	}

	
	
}
