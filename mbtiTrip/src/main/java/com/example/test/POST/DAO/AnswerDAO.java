package com.example.test.POST.DAO;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.paging.Criteria;



@Repository
public class AnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> answer) {
		int result = sqlSessiontemplate.insert("answer.insert", answer);
		return result;
	}

	public AnswerDTO save(AnswerDTO answerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<AnswerDTO> findById(Integer answerID) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AnswerDTO answerDto) {
		// TODO Auto-generated method stub
		
	}

	
}
