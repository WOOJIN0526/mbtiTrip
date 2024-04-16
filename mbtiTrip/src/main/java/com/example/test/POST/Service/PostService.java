package com.example.test.POST.Service;

import java.security.Principal;
import java.util.List;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;

import com.example.test.User.DTO.UserDTO;

import com.example.test.paging.Page;


public interface PostService {

		//게시글 목록 조회
		public List<PostDTO> list(Page page) throws Exception;

		//게시글 쓰기
		public int create(PostDTO post) throws Exception;

		//게시글 읽기
		public PostDTO getPost(Integer postId,Principal principal) throws Exception;

		//게시글 수정
		public void  modify(PostDTO post) throws Exception;

		//게시글 삭제
		public void remove(Integer postId) throws Exception;

		//게시글 검색
		public List<PostDTO> search(String keyword);
		
		public List<PostDTO> search(Page page) throws Exception;

		//전체 게시글 수
		public Integer totalCount() throws Exception;

		//추천
		public void suggestion(PostDTO item, UserDTO user) throws Exception;
		 
		
		//게시글카테고리 아이디 
		public List<PostDTO> findPostByCategoryID(PostDTO postDTO);

		//댓글 등록
		public void replyRegister(AnswerDTO reply) throws Exception;

		//댓글 목록 조회
		public List<AnswerDTO> replyList(PostDTO postId) throws Exception;

		//댓글 수정
		public void replyModify(AnswerDTO reply) throws Exception;

		//댓글 삭제
		public void replyRemove(Integer answerId) throws Exception;
		
		
}
