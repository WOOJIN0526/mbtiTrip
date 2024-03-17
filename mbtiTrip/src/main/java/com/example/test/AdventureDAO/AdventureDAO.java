package com.example.test.AdventureDAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdventureDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure) {
		int result = this.sqlSessiontemplate.insert("adventure.insert",Adventure);
		return result;
	}
}
