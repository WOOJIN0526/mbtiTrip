package com.example.test.POST.DAO;

import java.util.List;
import java.util.Map;

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
	};
	
	public List<Post_CategoryDTO> categoryName;
	
}
