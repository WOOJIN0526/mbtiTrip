package com.example.test.ReplaceReviewAnswer;


import com.example.test.User.DTO.UserDTO;

import com.example.test.replace.DTO.ReplaceReviewDTO;

public interface ReplaceReviewAnswerService {

	 public ReplaceReviewAnswerDTO create(ReplaceReviewDTO replaceReviewDto, String content, UserDTO writer);

	 public ReplaceReviewAnswerDTO getAnswer(Integer answerID);
	 
	 public ReplaceReviewAnswerDTO modify(ReplaceReviewAnswerDTO rpraDto, String content);
	 
	 public void delete(ReplaceReviewAnswerDTO rpraDto);
}
