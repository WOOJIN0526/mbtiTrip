package com.example.test.POST.Service;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

public interface AnswerService {

	 public AnswerDTO create(PostDTO questionDto, String content, UserDTO author);

	 public AnswerDTO getAnswer(Integer answerid);
	 
	 public AnswerDTO modify(AnswerDTO answerDto, String content);
	 
	 public void delete(AnswerDTO answerDto);
	 
	 public AnswerDTO vote(AnswerDTO answerDto, UserDTO siteUserDto);
}
