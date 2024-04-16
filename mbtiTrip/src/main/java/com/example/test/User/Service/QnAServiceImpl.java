package com.example.test.User.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.QnADAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.QAnswerDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserDTO;
import com.example.testExcepion.Cart.CartException;
import com.example.testExcepion.Cart.CartExceptionEnum;
import com.example.testExcepion.Insert.InsertException;
import com.example.testExcepion.Utile.UserNotFoundExcepiton;
import com.example.testExcepion.Utile.UtileExceptionCode;
import com.example.testExcepionQnA.QnAException;
import com.example.testExcepionQnA.QnAExceptionEnum;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class QnAServiceImpl implements QnAService {

	@Autowired 
	QnADAO qnaDao;
	@Autowired
	UserDAO userDao;
	
	@Override
	public int createQ(QnADTO qna, Principal principal) throws InsertException{
		log.info("creatQ ===> {}", qna.toString());
		if(principal.getName() == null) {
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		qnAvalidation(qna);
		String userName = userDao.getUserNameByuserID(principal.getName());
		qna.setUserName(userName);
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
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		String userName = userDao.getUserNameByuserID(prin.getName());
		return qnaDao.getMyQnA(userName);
	}

	@Override
	public boolean updateAnswer(QAnswerDTO answer, Principal principal) throws QnAException {
		if(principal.getName()==null) {
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		answervalidation(answer);
		String userName = userDao.getUserNameByuserID(principal.getName());
		answer.setAdminName(userName);
		answer.setAupdateDate(LocalDateTime.now());	
		int ck =qnaDao.createAnswer(answer);
		boolean cheak = (ck!= 0)? true : false;
		if(!cheak) {
			throw new QnAException(QnAExceptionEnum.QnA_INTERNAL_ERROR);
		}
		return cheak;
	}

	
	
	private void qnAvalidation(QnADTO qna) {
		
		if(qna.getTitle() ==null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_TITLE);
		}
		if(qna.getContents() == null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_CONTENTS);
		}
	}
	
	private void answervalidation(QAnswerDTO answer) {
		if(answer.getA_content() == null) {
			throw new QnAException(QnAExceptionEnum.QnA_NOT_CONTENTS);
		}
	}

	
	private boolean qnATitleValidation(QnADTO qna) {
		String title = qna.getTitle();
		boolean vaild = Pattern.matches("^[a-zA-Z0-9가-힣]*$", title);
		//vaild가 true면 특수문자가 없다는 겨 
		// 그니까 특수문자가 없을 땐 그냥 지나치도록, 반대값 리턴, == true = 특수문자 존재 
		return !vaild;
	}
	
	
}
