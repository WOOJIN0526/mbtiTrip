package com.example.test.AdventureAnswer;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdventureAnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> adventureAnswer) {
		int result = sqlSessiontemplate.insert("adventure.insert", adventureAnswer);
		return result;
	}

	public AdventureAnswerDTO save(AdventureAnswerDTO answerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<AdventureAnswerDTO> findById(Integer adventureID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("adventureAnswer.findById", adventureID);
	}

	public void delete(AdventureAnswerDTO adventureAnswerDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("adventureAnswer.delete", adventureAnswerDto);
	}
}
