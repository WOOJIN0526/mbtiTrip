package com.example.test.POST.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.PostAttach;

import com.example.test.POST.DAO.PostDAO2;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerDTO2;
import com.example.test.POST.DTO.PostDTO2;
import com.example.test.paging.Page;


@Service
public class PostServiceImpl2 implements PostService2{

	@Autowired
	PostDAO2 postDao;

	//게시글 목록 조회
	@Override
	public List<PostDTO2> list() throws Exception {
		// TODO Auto-generated method stub
		return postDao.list();
	}

	//게시글 쓰기
	@Override
	public void register(PostDTO2 post) throws Exception {
		// TODO Auto-generated method stub
		postDao.create(post);
	}

	//게시글 읽기
	@Override
	public PostDTO2 read(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		return postDao.read(postId);
	}

	//게시글 수정
	@Override
	public void modify(PostDTO2 post) throws Exception {
		// TODO Auto-generated method stub
		postDao.update(post);
	}

	//게시글 삭제
	@Override
	public void remove(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		postDao.delete(postId);
	}

	//게시글 검색
	@Override
	public List<PostDTO2> search(String keyword) {
		// TODO Auto-generated method stub
		return postDao.search(keyword);
	}

	//파일 업로드
	@Override
	public void uploadFile(PostAttach attach) throws Exception {
		// TODO Auto-generated method stub
		postDao.uploadFile(attach);
	}

	//파일 수정 업로드
	@Override
	public void uploadModifyFile(PostAttach attach) throws Exception {
		// TODO Auto-generated method stub
		postDao.uploadUpdateFile(attach);
	}

	@Override
	public List<PostAttach> readFileList(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		return postDao.readFileList(postId);
	}

	@Override
	public PostAttach readFile(Integer fileNo) throws Exception {
		// TODO Auto-generated method stub
		return  postDao.readFile(fileNo);
	}

	//파일 삭제
	@Override
	public void deleteFile(Integer fileNo) throws Exception {
		// TODO Auto-generated method stub
		postDao.deleteFile(fileNo);
	}

	//전체 게시글 수
	@Override
	public Integer totalCount(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return postDao.totalCountByKeyword(keyword);
	}
	
	@Override
	//전체 게시글 수
	public Integer totalCount() throws Exception{
		return postDao.totalCount();
	}
	// [페이지] 게시글 목록
	@Override
	public List<PostDTO2> list(Page page) throws Exception {
		// TODO Auto-generated method stub
		return postDao.listWithPage(page);
	}
	// [검색어][페이지] 게시글 검색
	@Override
	public List<PostDTO2> search(Page page) throws Exception {
		// TODO Auto-generated method stub
		return postDao.searchWithPage(page);
	}

	//댓글 등록
	@Override
	public void replyRegister(AnswerDTO2 reply) throws Exception {
		// TODO Auto-generated method stub
		postDao.replyCreate(reply);
	}

	//댓글 목록 조회
	@Override
	public List<AnswerDTO2> replyList(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		return postDao.replyList(postId);
	}

	//댓글 수정
	@Override
	public void replyModify(AnswerDTO2 reply) throws Exception {
		// TODO Auto-generated method stub
		postDao.replyUpdate(reply);
	}

	//댓글 삭제
	@Override
	public void replyRemove(Integer answerId) throws Exception {
		// TODO Auto-generated method stub
		postDao.replyDelete(answerId);
	}

	//조회수 증가
	@Override
	public void views(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		postDao.views(postId);
	}
	
	
}
