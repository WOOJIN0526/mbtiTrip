package com.example.test.POST.DAO;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.PostAttach;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerDTO2;
import com.example.test.POST.DTO.PostDTO2;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Page;

@Repository
public class PostDAO2 {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	

	@Autowired
	UserHistoryService userHistoryService;


	public List<PostDTO2> list() {
		// TODO Auto-generated method stub
		return null;
	}


	public void create(PostDTO2 post) {
		// TODO Auto-generated method stub
		
	}


	public PostDTO2 read(Integer postId) {
		return null;
		
		
		// TODO Auto-generated method stub
		
	}


	public void update(PostDTO2 post) {
		// TODO Auto-generated method stub
		
	}


	public void delete(Integer postId) {
		// TODO Auto-generated method stub
		
	}


	public List<PostDTO2> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


	public void uploadFile(PostAttach attach) {
		// TODO Auto-generated method stub
		
	}


	public void uploadUpdateFile(PostAttach attach) {
		// TODO Auto-generated method stub
		
	}


	public void deleteFile(Integer fileNo) {
		// TODO Auto-generated method stub
		
	}


	public Integer totalCountByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer totalCount() {
		// TODO Auto-generated method stub
		return null;
	}


	public List<PostDTO2> listWithPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<PostDTO2> searchWithPage(Page page) {
		// TODO Auto-generated method stub
		return null;
	}


	public void replyCreate(AnswerDTO2 reply) {
		// TODO Auto-generated method stub
		
	}


	public List<AnswerDTO2> replyList(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}


	public void replyUpdate(AnswerDTO2 reply) {
		// TODO Auto-generated method stub
		
	}


	public void replyDelete(Integer answerId) {
		// TODO Auto-generated method stub
		
	}


	public void views(Integer postId) {
		// TODO Auto-generated method stub
		
	}


	public List<PostAttach> readFileList(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}


	public PostAttach readFile(Integer fileNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
