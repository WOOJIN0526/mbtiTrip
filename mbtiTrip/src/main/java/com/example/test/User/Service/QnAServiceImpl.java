package com.example.test.User.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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

	
	/*Questinon 생성 메소드 */
	@Override
	public int createQ(QnADTO qna, Principal principal) throws InsertException{
		log.info("creatQ ===> {}", qna.toString());
		//로그인 한 경우 등록 가능
		if(principal.getName() == null) {
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		//validation Ck
		qnAvalidation(qna);
		//유저 정보 조회
		String userName = userDao.getUserNameByuserID(principal.getName());
		qna.setUserName(userName);
		qna.setUpdateDate(LocalDateTime.now());
		int result = qnaDao.create(qna);
		return result;
		
	}

	
	//고객센터 입장시 QnA리스트 로딩 
	@Override
	public List<QnADTO> getList(QnADTO qna) throws QnAException {
		/*리스트 초기화 */
		List<QnADTO> result = null;
		
		//Bean 객체인 SecurityContextHodler 내 authenticaion 객체 빼오기 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		
		if(auth != null) {
			log.info("권한에 따른 분기 처리");
			if(auth!= null && auth.isAuthenticated()) {
				log.info("접속한 User의 권한 확인  =>{}", auth);
				for(GrantedAuthority au : auth.getAuthorities()) {
					switch(au.getAuthority()) {
					/* 사용자의 권한에 따른 분기, admin 경우일 때만 getAdminList실행하여, 
					 * 답변이 달리지 않은 QnA도 확인이 가능 
					 * 이 외의 권한을 가진 사람은, 일반적으로 Answer이 등록된 QnA만 볼 수 있음
					 * */
					case "ROLE_USER": case "ROLE_BIS": case "ROLE_ANONYMOUS" :
						result =qnaDao.getList(qna); 
						break;
					case "ROLE_ADMIN": result= qnaDao.getAdminList(qna); break;
					}
			
				}
			}
		}
		else {
			/*권한이 샐 경우를 대비하여, 여기서 일반 List 호출 */
			result =qnaDao.getList(qna); 
		}
		//제대로 QnA를 호출하지 못할 경우, 예외처리 
		if(result==null) {
			throw new QnAException(QnAExceptionEnum.QnA_INTERNAL_ERROR);
		}	
		return result;
	}

	@Override
	public Map<String, Object> QnAdetail(Integer qID) {
		return qnaDao.getDetail(qID);
	}

	
	//UserHisyory에 사용 될 나의 QnA 불러오기 
	@Override
	public List<HashMap<String, Object>> getMyQnA(Principal prin) {
		if(prin.getName()==null) {
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		String userName = userDao.getUserNameByuserID(prin.getName());
		List<HashMap<String, Object>> result =qnaDao.getMyQnA(userName);
		return result;
	}

	
	/*Answer 삽입 메소드 */
	@Override
	public boolean updateAnswer(QAnswerDTO answer, Principal principal) throws QnAException {
		if(principal.getName()==null) {
			throw new UserNotFoundExcepiton(UtileExceptionCode.USER_NOT_FOUND_EXCEPTION);
		}
		//answer validation 검사 
		answervalidation(answer);
		//Admin 정보 세팅
		String userName = userDao.getUserNameByuserID(principal.getName());
		answer.setAdminName(userName);
		answer.setAupdateDate(LocalDateTime.now());	
		int ck =qnaDao.createAnswer(answer);
		//업데이트 결과에 따른 분기 처리 
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
