package com.example.test.AdventureReviewAnswer;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdventureReviewAnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> adventureReviewAnswer) {
		int result = sqlSessiontemplate.insert("adventureReview.insert", adventureReviewAnswer);
		return result;
	}

	public AdventureReviewAnswerDTO save(AdventureReviewAnswerDTO answerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<AdventureReviewAnswerDTO> findById(Integer adventureID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("adventureReviewAnswer.findById", adventureID);
	}

	public void delete(AdventureReviewAnswerDTO adventureAnswerDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("adventureReviewAnswer.delete", adventureAnswerDto);
	}
}
