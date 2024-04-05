package com.example.test.ReplaceAnswer;


import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface ReplaceAnswerService {

	 public ReplaceAnswerDTO create(ReplaceDTO replaceDto, String content, UserDTO writer);

	 public ReplaceAnswerDTO getAnswer(Integer answerID);
	 
	 public ReplaceAnswerDTO modify(ReplaceAnswerDTO rpaDto, String content);
	 
	 public void delete(ReplaceAnswerDTO rpaDto);
}
