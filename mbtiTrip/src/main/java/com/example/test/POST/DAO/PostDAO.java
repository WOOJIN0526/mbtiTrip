package com.example.test.POST.DAO;







import java.util.List;
import java.util.Optional;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;





@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	

	@Autowired
	UserHistoryService userHistoryService;
	
	public int insert(PostDTO post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		userHistoryService.ViewCreatePost();
		return result;
	}

	public List<PostDTO> list() {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("post.list");
	}

	public void create(PostDTO post) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.insert("post.create", post);
	}

	public Optional<PostDTO> findById(Integer postId) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("post.findById", postId);
	}

	public void update(PostDTO post) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("post.update", post);
	}

	public void delete(Integer postId) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.delete("post.delete", postId);
	}

	public List<PostDTO> search(String keyword) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("post.searchByKeyword", keyword);
	}

	public List<PostDTO> search(Page page) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("post.searchByPage", page);
	}

	public Integer totalCount() {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("post.totalCount");
	}

	public List<PostDTO> findByPostCategoryID(Long postCategoryID) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("post.findByPostCategoryId", postCategoryID);
	}

	public void replyCreate(AnswerDTO reply) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.insert("post.replyCreate", reply);
	}

	public List<AnswerDTO> replyList(PostDTO postId) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectList("post.replyList", postId);
	}

	public void replyUpdate(AnswerDTO reply) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("post.replyUpdate", reply);
	}

	public void replyDelete(Integer answerId) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.delete("post.replyDelete", answerId);
	}
	
	

	


	



	
	





	
	
	
}
