package com.example.test.AdventureDAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Adventure_ReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure_Review) {
		int result = this.sqlSessiontemplate.insert("adventure_review.insert",Adventure_Review);
		return result;
	}
}
