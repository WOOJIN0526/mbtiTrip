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
import com.example.testExcepion.Cart.CartException;
import com.example.testExcepion.Cart.CartExceptionEnum;
import com.example.testExcepion.Insert.InsertException;
import com.example.testExcepionQnA.QnAException;
import com.example.testExcepionQnA.QnAExceptionEnum;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QnAServiceImpl implements QnAService {

	@Autowired 
	QnADAO qnaDao;
	
	@Override
	public int createQ(QnADTO qna, Principal principal) throws InsertException{
		log.info("creatQ ===> {}", qna.toString());
		if(principal.getName() == null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_FOUND_USER);
		}
		if(qna.getTitle().isEmpty()) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_TITLE);
		}
		if(qna.getContents().isEmpty()) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_CONTENTS);
		}
		qna.setUserName(principal.getName());
		qna.setUpdateDate(LocalDateTime.now());
		return qnaDao.create(qna);
		
	}

	@Override
	public List<QnADTO> getList(QnADTO qna) throws QnAException {
		List<QnADTO> result = qnaDao.getList(qna);
		if(result.isEmpty()) {
			throw new QnAException(QnAExceptionEnum.QnA_INTERNAL_ERROR);
		}	
		return result;
	}

	@Override
	public Map<String, Object> QnAdetail(Integer qID) {
		return qnaDao.getDetail(qID);
	}

	@Override
	public List<HashMap<String, Object>> getMyQnA(Principal prin) {
		if(prin.getName()==null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_FOUND_USER);
		}
		String userName = prin.getName();
		return qnaDao.getMyQnA(userName);
	}

	@Override
	public boolean updateAnswer(QAnswerDTO answer, Principal principal) throws QnAException {
		if(principal.getName()==null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_FOUND_USER);
		}
		answer.setAdminName(principal.getName());
		answer.setAupdateDate(LocalDateTime.now());	
		int ck =qnaDao.createAnswer(answer);
		boolean cheak = (ck!= 0)? true : false;
		if(!cheak) {
			throw new QnAException(QnAExceptionEnum.QnA_INTERNAL_ERROR);
		}
		return cheak;
	}

}
