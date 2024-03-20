package com.example.test.POST.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.Post;
import com.example.test.POST.DTO.PostPage;

@Service
public class PostServiceImpl2 implements PostService2 {

	@Autowired
	PostDAO postDAO;
	
	@Override
	public List<Post> list() throws Exception {
		// TODO Auto-generated method stub
		return postDAO.list();
	}

	@Override
	public void register(Post post) throws Exception {
		postDAO.create(post);
		
		int boardNo = postDAO.maxSeqNo();
		post.setGroupNo(boardNo);
		post.setPostNo(boardNo);
		
		postDAO.updateGroupNo(post);
	}

	@Override
	public Post read(Integer postNo) throws Exception {
		return postDAO.read(postNo);
	}

	@Override
	public void modify(Post post) throws Exception {
		postDAO.update(post);
		
	}

	@Override
	public void remove(Integer postNo) throws Exception {
		postDAO.delete(postNo);
		
	}

	@Override
	public List<Post> search(String keyword) {
		return postDAO.search(keyword);
	}

	@Override
	public Integer totalCount() throws Exception {
		return postDAO.totalCount();
	}

	@Override
	public Integer totalCount(String keyword) throws Exception {
		return postDAO.totalCount(keyword);
	}

	@Override
	public List<Post> list(PostPage page) throws Exception {
		return postDAO.listWithPage(page);
	}

	@Override
	public List<Post> search(PostPage page) throws Exception {
		return postDAO.listWithPage(page);
	}

	@Override
	public void replyRegister(AnswerDTO answerDTO) throws Exception {
		postDAO.answerCreate(answerDTO);
		
	}

	@Override
	public List<AnswerDTO> replyList(Integer boardNo) throws Exception {
		return postDAO.replyList(boardNo);
	}

	@Override
	public void replyModify(AnswerDTO answerDTO) throws Exception {
		postDAO.update(answerDTO);
		
	}

	@Override
	public void replyRemove(Integer reply_no) throws Exception {
		postDAO.delete(reply_no);
		
	}

	@Override
	public void updateGroupNo(Post post) throws Exception {
		postDAO.updateGroupNo(post);
		
	}

	@Override
	public void answerRegister(Post post) throws Exception {
		// 계층번호
				int depthNo = postDAO.readDepthNo(post.getPostNo());
				// 계층번호 = 부모글의 계층번호 + 1
				post.setDepthNo(depthNo+1);
				
				
				// 부모글이 답글인 경우
				if( post.getSeqNo() == 0 ) {
					// 순서번호의 MAX
					int maxSeqNo = postDAO.maxSeqNo();
					post.setSeqNo(maxSeqNo+1);
				}
				
				postDAO.answerCreate(post);
		
	}

	@Override
	public int countAnswer(Integer groupNo) throws Exception {
		return postDAO.countAnswer(groupNo);
	}

	@Override
	public int readDepthNo(Integer postNo) throws Exception {
		return postDAO.readDepthNo(postNo);
	}

	@Override
	public int readGroupNo(Integer postNo) throws Exception {
		return postDAO.readGroupNo(postNo);
	}

	@Override
	public int maxSeqNo() throws Exception {
		return postDAO.maxSeqNo();
	}

	@Override
	public void view(Integer postNo) throws Exception {
		postDAO.view(postNo);
		
	}

}
