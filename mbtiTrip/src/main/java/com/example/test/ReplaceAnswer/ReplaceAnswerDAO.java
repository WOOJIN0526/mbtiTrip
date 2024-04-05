package com.example.test.ReplaceAnswer;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplaceAnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> replaceAnswer) {
		int result = sqlSessiontemplate.insert("replaceAnswer.insert", replaceAnswer);
		return result;
	}

	public ReplaceAnswerDTO save(ReplaceAnswerDTO answerDto) {
		// TODO Auto-generated method stub
		return answerDto;
	}

	public Optional<ReplaceAnswerDTO> findById(Integer answerID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("replaceAnswer.findById", answerID);
	}

	public void delete(ReplaceAnswerDTO rpaDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("replaceAnswer.delete", rpaDto);
	}
}
