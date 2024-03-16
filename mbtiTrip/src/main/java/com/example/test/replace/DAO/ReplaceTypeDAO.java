package com.example.test.replace.DAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Repository
public class ReplaceTypeDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> replaceType) {
		int result = this.sqlSessiontemplate.insert("replaceType.insert",replaceType);
		return result;
	}
	

//	
}
