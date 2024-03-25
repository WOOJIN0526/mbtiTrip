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
import com.example.test.POST.DTO.Post_CategoryDTO;
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
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	private Specification<PostDTO> search(String kw, String categoryName) {
        return new Specification<PostDTO>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public Predicate toPredicate(Root<PostDTO> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거 
                Join<PostDTO, UserDTO> u1 = q.join("author", JoinType.LEFT);
                Join<PostDTO, AnswerDTO> a = q.join("answerList", JoinType.LEFT);
                Join<PostDTO, Post_CategoryDTO> c = q.join("category", JoinType.LEFT);
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
	public Page<PostDTO> getList(int page, String kw, String categoryName) {
		 List<Sort.Order> sorts = new ArrayList<>();
	     sorts.add(Sort.Order.desc("createDate"));
	     Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	     Specification<PostDTO> spec = search(kw, categoryName);
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

	//추천수 기능
	@Override
	public PostDTO vote(PostDTO postDto, UserDTO userDto) {
			postDto.getVoter().add(userDto);
	        
	        return this.postDAO.save(postDto);
	}

	// 게시물을 조회하고 조회수 증가
	@Transactional
	public PostDTO detail(Integer id, HttpServletRequest request, HttpServletResponse response) {
		 Cookie oldCookie = null;
		    Cookie[] cookies = request.getCookies();
		    if (cookies != null)
		        for (Cookie cookie : cookies)
		            if (cookie.getName().equals("postView"))
		                oldCookie = cookie;

		    if (oldCookie != null) {
		        if (!oldCookie.getValue().contains("[" + id.toString() + "]")) {
		            postDAO.updateCount(id);
		            oldCookie.setValue(oldCookie.getValue() + "_[" + id + "]");
		            oldCookie.setPath("/");
		            oldCookie.setMaxAge(60 * 60 * 24);
		            response.addCookie(oldCookie);
		        }
		    }
		    else {
		        postDAO.updateCount(id);
		        Cookie newCookie = new Cookie("boardView","[" + id + "]");
		        newCookie.setPath("/");
		        newCookie.setMaxAge(60 * 60 * 24);
		        response.addCookie(newCookie);
		    }

		    return postDAO.findById(id).orElseThrow(() -> {
		        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		    });
		}
	
}
