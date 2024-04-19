package com.example.test.POST.DAO;







import java.util.HashMap;
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
import com.example.test.paging.PaginationVo;





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



	public int create(PostDTO post) {
		System.out.println(post.toString());
		int result = this.sqlSessiontemplate.insert("post.create", post);
		System.out.println(result);
		userHistoryService.ViewCreatePost();
		return result;
	}

	public PostDTO findById(Integer postId) {
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

//	public List<PostDTO> findByPostCategoryID(PostDTO postDTO) {
//		// TODO Auto-generated method stub
//		return this.sqlSessiontemplate.selectList("post.findByPostCategoryId", postDTO);
//	}

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
	
	public int titleCk(String title) {
		int ck =this.sqlSessiontemplate.selectOne("post.titleCk", title);
		return ck;
	}



	public List<PostDTO> getListPage(Page page) {
		// TODO Auto-generated method stub
		System.out.println(page.getPostCateGoryID()+"here");
		return this.sqlSessiontemplate.selectList("post.getListPage", page);
	}
	
	

	public int getCount() {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("post.getCount");
	}
	




	
	





	
	
	
}
