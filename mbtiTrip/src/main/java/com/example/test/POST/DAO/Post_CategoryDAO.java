package com.example.test.POST.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.Post_CategoryDTO;

@Repository
public class Post_CategoryDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post_category) {
		int result = sqlSessiontemplate.insert("post_category.insert", post_category);
		return result;
	}

	public List<Post_CategoryDTO> getList() {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("post_category.getList");
	}

	public Post_CategoryDTO getCategory(String category) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("post_category.getCategory", category);
	}
	

	
}
