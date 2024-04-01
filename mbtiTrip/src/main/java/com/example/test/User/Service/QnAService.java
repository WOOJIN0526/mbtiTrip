package com.example.test.User.Service;

import java.util.List;
import java.util.Map;

import com.example.test.User.DTO.QnADTO;

public interface QnAService {

	int createQ(QnADTO qna);

	List<QnADTO> getList(QnADTO qna);

	QnADTO QnAdetail(Integer qID);

	Map<String, Object> getMyQnA(String userName);

}
