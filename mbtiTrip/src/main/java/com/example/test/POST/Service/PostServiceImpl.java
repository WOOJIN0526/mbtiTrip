package com.example.test.POST.Service;


import lombok.AllArgsConstructor;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.Answer;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.Post;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.Predicate;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;




@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;

//	private Specification<Post> search(String kw){
//		return new Specification<Post>() {
//			private static final long SerialVersionUID = 1L;
//			@Override
//			 public Predicate toPredicate(Root<Post> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
//                query.distinct(true);  // 중복을 제거 
//                Join<Post, UserDTO> u1 = q.join("author", JoinType.LEFT);
//                Join<Post, Answer> a = q.join("answerList", JoinType.LEFT);
//                Join<Answer, UserDTO> u2 = a.join("author", JoinType.LEFT);
//                return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목 
//                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용 
//                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자 
//                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용 
//                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자 
//            }
//        };
//    }

	 //private final ModelMapper modelMapper;
	  
	 //private PostDTO of(Post post) {
	        //return modelMapper.map(post, PostDTO.class);
	    //}
	 
	 //private Post of(PostDTO postDTO) {
	        //return modelMapper.map(postDTO, Post.class);
	    //}
	 
//	@Override
//	public Page<PostDTO> getList(int page, String kw) {
//		 List<Sort.Order> sorts = new ArrayList<>();
//		 List<Sort.Order> sorts1 = new ArrayList<>();
//	     sorts1.add(Sort.Order.desc("createDate"));
//		 Pageable pageable = (Pageable) PageRequest.of(page, 10, Sort.by(sorts1));
//		 Specification<Post> spec = search(kw);
//			return this.postDAO.findAll(spec,pageable);
//	}

//
//	@Override
//	public Predicate toPredicate(Root<Post> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public PostDTO create(String title, String content, UserDTO user) {
		 PostDTO postDto = new PostDTO();
	        postDto.setTitle(title);
	        postDto.setContent(content);
	        postDto.setUpdateDate(LocalDateTime.now());
	        postDto.setAuthor(user);
	        this.postDAO.save(postDto);
	        return postDto;
	}

	@Override
	public PostDTO modify(PostDTO postDTO, String title, String content) {
		 	postDTO.setTitle(title);
	        postDTO.setContent(content);
	        postDTO.setModifyDate(LocalDateTime.now());
	        this.postDAO.save(postDTO);
	        return postDTO;
	}

	@Override
	public void delete(PostDTO postDTO) {
		 this.postDAO.delete(postDTO);
		
	}

	@Override
	public PostDTO vote(PostDTO postDTO, UserDTO userDTO) {
		 	postDTO.getVoter().add(userDTO);
	        this.postDAO.save(postDTO);
	        return postDTO;
	}

	
	
}
