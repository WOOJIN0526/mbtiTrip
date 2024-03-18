package com.example.test.AdventureDAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class Adventure_TypeDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> adventure_type) {
		int result = this.sqlSessiontemplate.insert("adventure_type.insert",adventure_type);
		return result;
	}
}
