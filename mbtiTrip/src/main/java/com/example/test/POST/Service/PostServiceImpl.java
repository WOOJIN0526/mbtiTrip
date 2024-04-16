package com.example.test.POST.Service;




import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Page;
import com.example.testExcepion.Insert.InsertException;
import com.example.testExcepion.Insert.InsertExceptionEnum;
import com.example.testExcepion.Post.PostException;
import com.example.testExcepion.Post.PostExceptionEnum;
import com.example.testExcepion.updated.UpdateException;
import com.example.testExcepion.updated.UpdateExceptionEnum;






@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	UserHistoryService userHistoryService;






	
	@Override
	public List<PostDTO> list() throws Exception {
		// TODO Auto-generated method stub
		return postDAO.list();
	}



	@Override
	public List<PostDTO> list(Page page) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.listWithPage(page);
	}



	@Override
	public void create(PostDTO post)  {
		postValidationCK(post);
		try {
			postDAO.create(post);
		}catch(Exception e) {
			throw new InsertException(InsertExceptionEnum.INSERT_SERVER_ERROR);
		}
		 
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
		postValidationCK(post);
		try {
			postDAO.update(post);
		}catch(Exception e) {
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_UPDATE);
		}
	}



	@Override
	public void remove(Integer postId) throws Exception {
		// TODO Auto-generated method stub
	
		try {
			postDAO.delete(postId);
		}catch(Exception e) {
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_DELETE);
		}
	}



	@Override
	public List<PostDTO> search(String keyword) {
		List<PostDTO> postLi = postDAO.search(keyword);
		if(postLi.isEmpty()) {
			throw new PostException(PostExceptionEnum.POST_NOT_FOUND);
		}
		return postLi;
	}



	@Override
	public List<PostDTO> search(Page page) throws Exception {
		List<PostDTO> postLi = postDAO.search(page);
		if(postLi.isEmpty()) {
			throw new PostException(PostExceptionEnum.POST_NOT_FOUND);
		}
		return postLi;
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
	public List<PostDTO> findPostByCategoryID(PostDTO postDTO) {
	// 게시글 목록 조회

	List<PostDTO> post = postDAO.findByPostCategoryID(postDTO);
	if(post.isEmpty()) {
		throw new PostException(PostExceptionEnum.POST_NOT_FOUND);
	}

	return post;
	        
	}



	@Override
	public void replyRegister(AnswerDTO reply) throws Exception {
		if(reply.getWriter().getUsername() == null) {
			throw new PostException(PostExceptionEnum.POST_NOT_FOUND_USER);
		}
		if(reply.getContent().length()>500) {
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_ContentsSize);
		}
		try {
			postDAO.replyCreate(reply);
		}
		catch(Exception e){
			throw new PostException(PostExceptionEnum.POST_UNKNOWN_SEVER_ERROR);
		}
		
	}



	@Override
	public List<AnswerDTO> replyList(PostDTO postId) throws Exception {
		List<AnswerDTO> replyLi =  postDAO.replyList(postId);
		if(replyLi.isEmpty()) {
			throw new PostException(PostExceptionEnum.POST_NOT_FOUND);
		}
		return replyLi;
	}



	@Override
	public void replyModify(AnswerDTO reply) throws Exception {
		if(reply.getWriter().getUsername() == null) {
			throw new PostException(PostExceptionEnum.POST_NOT_FOUND_USER);
		}
		if(reply.getContent().length()>500) {
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_ContentsSize);
		}
		try {
			postDAO.replyUpdate(reply);
		} catch (Exception e) {
			throw new UpdateException(UpdateExceptionEnum.UPDATE_FAIL_SERVER);
		}
	}



	@Override
	public void replyRemove(Integer answerId) throws Exception {
		try {
			postDAO.replyDelete(answerId);	
		} catch (Exception e) {
			// TODO: handle exception
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_DELETE);
		}
		
	}



	private void postValidationCK(PostDTO postDTO) {
		if(postDTO.getUserName() == null) {
			throw new PostException(PostExceptionEnum.POST_PERMISSION_DENIED);
		}
		switch(titleCk(postDTO)) {
			case 0 : break;
			case 1 : throw new PostException(PostExceptionEnum.POST_UNABLE_TO_TITLE);
			case 2 : throw new PostException(PostExceptionEnum.POST_UNABLE_TO_TITLE2);
			case 3:  throw new PostException(PostExceptionEnum.POST_UNABLE_TO_TITLE3);
		}
		if(postDTO.getContent().length() > 500) {
			throw new PostException(PostExceptionEnum.POST_UNABLE_TO_ContentsSize);
		}	
	}
	
	
	private int titleCk(PostDTO postDTO) {
		int check = 0;
		boolean ck = Pattern.matches("^[a-zA-Z0-9가-힣]*$", postDTO.getTitle());
		if(!ck) {
			check=1;
		}
		if(postDTO.getTitle().length() > 15){
			check=2;
		}
		if(postDAO.titleCk(postDTO.getTitle()) != 0) {
			check = 3;
		}
		return check;
	}





	

	
	





	
	
    

}
