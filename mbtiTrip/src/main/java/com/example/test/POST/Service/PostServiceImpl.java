package com.example.test.POST.Service;




import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Page;






@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	UserHistoryService userHistoryService;






	
	@Override
	public List<PostDTO> list(Page page) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.list();
	}



	@Override
	public void create(PostDTO post) throws Exception {
		// TODO Auto-generated method stub
		postDAO.create(post);
	}



	@Override
	public PostDTO getPost(Integer postId) throws Exception {
		Optional<PostDTO> post = this.postDAO.findById(postId);
		  if (post.isPresent()) {
	        	PostDTO postDto = post.get();        	
	        	postDto.setViews(postDto.getViews()+1);        	
	        	this.postDAO.create(postDto);
	            return postDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}



	@Override
	public void modify(PostDTO post) throws Exception {
		// TODO Auto-generated method stub
		postDAO.update(post);
	}



	@Override
	public void remove(Integer postId) throws Exception {
		// TODO Auto-generated method stub
		postDAO.delete(postId);
	}



	@Override
	public List<PostDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return postDAO.search(keyword);
	}



	@Override
	public List<PostDTO> search(Page page) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.search(page);
	}



	@Override
	public Integer totalCount() throws Exception {
		// TODO Auto-generated method stub
		return postDAO.totalCount();
	}

	//추천
	@Override
	public void suggestion(PostDTO postDto, UserDTO userDto) {
		postDto.getSuggestion().add(userDto);
        
        this.postDAO.create(postDto);
	}

    @Override
	public List<PostDTO> findPostByCategoryID(Long postCategoryID) {
	// 게시글 목록 조회
	List<PostDTO> post = postDAO.findByPostCategoryID(postCategoryID);
	return post;
	        
	}



	@Override
	public void replyRegister(AnswerDTO reply) throws Exception {
		// TODO Auto-generated method stub
		postDAO.replyCreate(reply);
	}



	@Override
	public List<AnswerDTO> replyList(PostDTO postId) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.replyList(postId);
	}



	@Override
	public void replyModify(AnswerDTO reply) throws Exception {
		// TODO Auto-generated method stub
		postDAO.replyUpdate(reply);
	}



	@Override
	public void replyRemove(Integer answerId) throws Exception {
		// TODO Auto-generated method stub
		postDAO.replyDelete(answerId);
	}



	





	

	
	





	
	
    

}
