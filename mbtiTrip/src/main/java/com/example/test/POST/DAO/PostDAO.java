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
import com.example.test.paging.Criteria;





@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(PostDTO post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}
	
	public int save(PostDTO postDto) {
		return sqlSessiontemplate.insert("post.save", postDto);
	}

	public Optional<PostDTO> findById(Integer userid) {
		
		return sqlSessiontemplate.selectOne("post.findById", userid);
	}

	

	public void delete(PostDTO postDto) {
		sqlSessiontemplate.delete("post.delete", postDto);
		
	}

	public List<PostDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("post.getList", cri);
	}

	public void updateCount(Integer postID) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("post.updateCount", postID);
	}

	public List<PostDTO> findByPostCategoryID(Long postCategoryID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post.findByPostCategoryID", postCategoryID);
	}

	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post.total", cri);
	}



	


	



	
	





	
	
	
}
