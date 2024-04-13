package com.example.test.User.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.test.User.DTO.QAnswerDTO;
import com.example.test.User.DTO.QnADTO;

public interface QnAService {

	int createQ(QnADTO qna, Principal principal);

	List<QnADTO> getList(QnADTO qna);

	Map<String, Object> QnAdetail(Integer qID);

	List<HashMap<String, Object>> getMyQnA(Principal prin);

	boolean updateAnswer(QAnswerDTO answer, Principal principal);

}
