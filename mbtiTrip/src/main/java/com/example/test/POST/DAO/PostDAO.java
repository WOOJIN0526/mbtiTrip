package com.example.test.POST.DAO;







import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Repository;


import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;





@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

	public Optional<PostDTO> findById(Integer userid) {
		
		return sqlSessiontemplate.selectOne("post.findById", userid);
	}

	public PostDTO save(PostDTO postDto) {
		return sqlSessiontemplate.selectOne("post.save", postDto);
		
	}

	public void delete(PostDTO postDto) {
		sqlSessiontemplate.delete("post.delete", postDto);
		
	}

	public List<PostDTO> list(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("post.list", cri);
	}

	public int listCount(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post.listCount", cri);
	}

	public void updateCount(Integer postID) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("post.updateCount", postID);
	}

	



	
	





	
	
	
}
