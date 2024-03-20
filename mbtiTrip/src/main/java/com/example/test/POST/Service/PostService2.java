package com.example.test.POST.Service;

import java.util.List;



import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.Post;
import com.example.test.POST.DTO.PostPage;

public interface PostService2 {

		// 게시글 목록 조회
		public List<Post> list() throws Exception;
		
		// 게시글 쓰기
		public void register(Post post) throws Exception;
		
		// 게시글 읽기
		public Post read(Integer postNo) throws Exception;
		
		// 게시글 수정
		public void modify(Post post) throws Exception;
		
		// 게시글 삭제
		public void remove(Integer postNo) throws Exception;
		
		// 게시글 검색
		public List<Post> search(String keyword);
		
		// 전체 게시글 수
		public Integer totalCount() throws Exception;
		
		// 전체 게시글 수
		public Integer totalCount(String keyword) throws Exception;
		
		// [페이지] 게시글 목록
		public List<Post> list(PostPage page) throws Exception;
		
		// [검색어][페이지] 게시글 검색
		public List<Post> search(PostPage page) throws Exception;
		
		// 댓글 등록
		public void replyRegister(AnswerDTO answerDTO) throws Exception;
		
		// 댓글 목록 조회
		public List<AnswerDTO> replyList(Integer boardNo) throws Exception;
		
		// 댓글 수정 
		public void replyModify(AnswerDTO answerDTO) throws Exception;
		
		//댓글 삭제
		public void replyRemove(Integer reply_no) throws Exception;
		
		
		// 그룹번호 수정
		public void updateGroupNo(Post post) throws Exception;
		
		// 답글 쓰기
		public void answerRegister(Post post) throws Exception;
		
		// 그룹번호 게시글 개수
		public int countAnswer(Integer groupNo) throws Exception;
		
		// 계층번호 조회
		public int readDepthNo(Integer postNo) throws Exception;
		
		// 계층번호 조회
		public int readGroupNo(Integer postNo) throws Exception;
		
		// 순서번호 MAX 조회
		public int maxSeqNo() throws Exception;
		
		// 조회수 증가
		public void view(Integer postNo) throws Exception;
}
