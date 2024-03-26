package com.example.test.User.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.User.DAO.QnADAO;
import com.example.test.User.DTO.QnADTO;

public class QnAServiceImpl implements QnAService {

	@Autowired 
	QnADAO qnaDao;
	
	@Override
	public void createQ(QnADTO qna) {
		qnaDao.create(qna);
		
	}

	@Override
	public List<QnADTO> getList(QnADTO qna) {
		
		return qnaDao.getList(qna);
	}s

	@Override
	public QnADTO QnAdetail(Integer qID) {
		return qnaDao.getDetail(qID);
	}

	@Override
	public Map<String, Object> getMyQnA(String userName) {
	
		return qnaDao.getMyQnA(userName);
	}

}
