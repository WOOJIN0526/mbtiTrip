package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.PostAttach;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerDTO2;
import com.example.test.POST.DTO.PostDTO2;
import com.example.test.paging.Page;

public interface PostService2 {

	//게시글 목록 조회
	public List<PostDTO2> list() throws Exception;

	//게시글 쓰기
	public void register(PostDTO2 post) throws Exception;

	//게시글 읽기
	public PostDTO2 read(Integer postId) throws Exception;

	//게시글 수정
	public void  modify(PostDTO2 post) throws Exception;

	//게시글 삭제
	public void remove(Integer postId) throws Exception;

	//게시글 검색
	public List<PostDTO2> search(String keyword);

	//파일 업로드
	public void uploadFile(PostAttach attach) throws Exception;

	//파일 수정 업로드
	public void uploadModifyFile(PostAttach attach) throws Exception;

	//파일 삭제
	public void deleteFile(Integer fileNo) throws Exception;

	//전체 게시글 수
	public Integer totalCount() throws Exception;
	
	//전체 게시글 수
	public Integer totalCount(String keyword) throws Exception;

	// [페이지] 게시글 목록
	public List<PostDTO2> list(Page page) throws Exception;
		
	// [검색어][페이지] 게시글 검색
	public List<PostDTO2> search(Page page) throws Exception;

	//댓글 등록
	public void replyRegister(AnswerDTO2 reply) throws Exception;

	//댓글 목록 조회
	public List<AnswerDTO2> replyList(Integer postId) throws Exception;

	//댓글 수정
	public void replyModify(AnswerDTO2 reply) throws Exception;

	//댓글 삭제
	public void replyRemove(Integer answerId) throws Exception;

	//조회수 증가
	public void views(Integer postId) throws Exception;

	// 파일 읽기 - 글번호
	public List<PostAttach> readFileList(Integer postId) throws Exception;
		
	// 파일 읽기 - 파일번호
	public PostAttach readFile(Integer fileNo) throws Exception;
}
