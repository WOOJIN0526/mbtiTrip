package com.example.test.POST.DAO;







import java.util.List;
import java.util.Optional;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;


import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.paging.Criteria;





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
	
	public List<PostDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("post.getList", cri);
	}
	
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post.getTotal", cri);
	}
	
	public Optional<PostDTO> findById(Integer userName) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post.findById", userName);
	}

	public void save(PostDTO postDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.insert("post.save", postDto);
	}

	public void create(PostDTO post) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.insert("post.create", post);
	}

	public void update(PostDTO post) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("post.update", post);
	}

	public void delete(PostDTO postDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("post.delete", postDto);
	}

	public void updateCount(Integer postID) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("post.updateCount", postID);
	}

	public List<PostDTO> findByPostCategoryID(Long postCategoryID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("post.findByPostCategoryID", postCategoryID);
	}

	public Integer suggestion(PostDTO postDto) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.update("post.suggestion", postDto);
	}

	


	



	
	





	
	
	
}
