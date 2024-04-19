package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.User.DTO.QAnswerDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.testExcepionQnA.QnAException;
import com.example.testExcepionQnA.QnAExceptionEnum;

import jakarta.inject.Inject;

@Repository
public class QnADAO { 

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int create(QnADTO qna) {
		int ch = 0;
		try {
			ch = this.sqlSessiontemplate.insert("qna.create", qna);
		} catch (Exception e) {
			throw new QnAException(QnAExceptionEnum.QnA_INTERNAL_ERROR);
		}
		return ch;
	}

	public List<QnADTO> getList(QnADTO qna) {
	List<QnADTO> result = this.sqlSessiontemplate.selectList("qna.getlist", qna);
	 return result;
	
	}
	
	public List<QnADTO> getAdminList(QnADTO qna) {
		List<QnADTO> result =this.sqlSessiontemplate.selectList("qna.getAdminList", qna);
		return result;
	}
	
	
	public Map<String, Object> getDetail(Integer qID) {
		Map<String, Object> result = this.sqlSessiontemplate.selectOne("qna.getDetail", qID);
		return result;
	}


	public List<HashMap<String, Object>> getMyQnA(String userName) {
		List<HashMap<String, Object>> result = this.sqlSessiontemplate.selectList("qna.getMyQnA", userName);
		return  result ;
	}

	
	public int createAnswer(QAnswerDTO answer) {
		 int Ck = this.sqlSessiontemplate.insert("qna.createAnswer", answer);
		 this.sqlSessiontemplate.update("qna.AnswerUpdate", answer);
		 return Ck;
	}

	
	
	
}
