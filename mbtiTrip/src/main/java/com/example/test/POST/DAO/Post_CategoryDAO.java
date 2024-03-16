package com.example.test.POST.DAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Post_CategoryDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post_category) {
		int result = sqlSessiontemplate.insert("post_category.insert", post_category);
		return result;
	};
}
