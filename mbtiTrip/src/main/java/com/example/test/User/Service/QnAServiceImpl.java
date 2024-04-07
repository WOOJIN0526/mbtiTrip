package com.example.test.User.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.QnADAO;
import com.example.test.User.DTO.QAnswerDTO;
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
	public Map<String, Object> QnAdetail(Integer qID) {
		return qnaDao.getDetail(qID);
	}

	@Override
	public List<HashMap<String, Object>> getMyQnA(String userName) {
	
		return qnaDao.getMyQnA(userName);
	}

	@Override
	public boolean updateAnswer(QAnswerDTO answer, Principal principal) {
		answer.setAdminName(principal.getName());
		answer.setAupdateDate(LocalDateTime.now());
		int ck =qnaDao.createAnswer(answer);
	
		
		
		return true;
	}

}
