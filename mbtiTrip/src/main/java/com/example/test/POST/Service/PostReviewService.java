package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Page;
import com.example.test.paging.PaginationVo;

public interface PostReviewService {

	public List<PostReviewDTO> getListPage(final PaginationVo pagination);

	//페이징을 위한 전체 데이터 개수 파악
	public int getCount();

	//게시글 쓰기
	public void create(PostReviewDTO post) throws Exception;

	//게시글 읽기
	public PostReviewDTO getPost(Integer postId) throws Exception;

	//게시글 수정
	public void  modify(PostReviewDTO post) throws Exception;

	//게시글 삭제
	public void remove(Integer postId) throws Exception;


	//추천
	public void suggestion(PostReviewDTO post, UserDTO user) throws Exception;
			 
			
	//게시글카테고리 아이디 
	public List<PostReviewDTO> findPostByCategoryID(Long postCategoryID);

	//댓글 등록
	public void replyRegister(AnswerDTO reply) throws Exception;

	//댓글 목록 조회
	public List<AnswerDTO> replyList(PostReviewDTO post) throws Exception;

	//댓글 수정
	public void replyModify(AnswerDTO reply) throws Exception;

	//댓글 삭제
	public void replyRemove(Integer answerId) throws Exception;
	
	//평점
	public void setRating(int itemID);
}
