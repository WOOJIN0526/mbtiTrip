package com.example.test.ReplaceReviewAnswer;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReplaceReviewAnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> replaceReviewAnswer) {
		int result = sqlSessiontemplate.insert("replaceReviewAnswer.insert", replaceReviewAnswer);
		return result;
	}

	public ReplaceReviewAnswerDTO save(ReplaceReviewAnswerDTO answerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ReplaceReviewAnswerDTO> findById(Integer answerID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("replaceReviewAnswer.findById", answerID);
	}

	public void delete(ReplaceReviewAnswerDTO rpraDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("replaceReviewAnswer.delete", rpraDto);
	}
}
