package com.example.test.AdventureDAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Adventure_CategoryDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure_Category) {
		int result = this.sqlSessiontemplate.insert("adventure_category.insert",Adventure_Category);
		return result;
	}
}
