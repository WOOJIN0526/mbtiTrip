package com.example.test.User.DAO;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.User.DTO.QnADTO;

import jakarta.inject.Inject;

@Repository
public class QnADAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public void create(QnADTO qna) {
		
		int ch = this.sqlSessiontemplate.insert("qna.create", qna);
		
	}

	public List<QnADTO> getList(QnADTO qna) {
		
		return this.sqlSessiontemplate.selectList("qna.getlist", qna);
	}

	public QnADTO getDetail(Integer qID) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("qna.getDetail", qID);
	}

	public Map<String, Object> getMyQnA(String userName) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectMap("qna.getMyQnA", userName);
	}

	
	
	
}
