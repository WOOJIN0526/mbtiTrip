package com.example.test.Mbti.DAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Repository
public class MbtiDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> mbti) {
		int result = this.sqlSessiontemplate.insert("mbti.insert",mbti);
		return result;
	}
	 

}
