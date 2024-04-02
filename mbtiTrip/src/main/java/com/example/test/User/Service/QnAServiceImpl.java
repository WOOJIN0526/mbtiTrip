package com.example.test.User.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.QnADAO;
import com.example.test.User.DTO.QnADTO;


@Service
public class QnAServiceImpl implements QnAService {

	@Autowired 
	QnADAO qnaDao;
	
	@Override
	public int createQ(QnADTO qna) {
		
		return qnaDao.create(qna);
		
	}

	@Override
	public List<QnADTO> getList(QnADTO qna) {
		
		return qnaDao.getList( qna);
	}

	@Override
	public QnADTO QnAdetail(Integer qID) {
		return qnaDao.getDetail(qID);
	}

	@Override
	public Map<String, Object> getMyQnA(String userName) {
	
		return qnaDao.getMyQnA(userName);
	}

}
