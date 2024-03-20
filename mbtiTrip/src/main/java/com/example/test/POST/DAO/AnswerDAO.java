package com.example.test.POST.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;

@Repository
public class AnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> answer) {
		int result = sqlSessiontemplate.insert("answer.insert", answer);
		return result;
	};
	
	
}
