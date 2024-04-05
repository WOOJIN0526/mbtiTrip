package com.example.test.AdventureReviewAnswer;


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.User.DTO.UserDTO;

public interface AdventureReviewAnswerService {

	 public AdventureReviewAnswerDTO create(Adventure_ReviewDTO adventureReviewDto, String content, UserDTO writer);

	 public AdventureReviewAnswerDTO getAnswer(Integer answerID);
	 
	 public AdventureReviewAnswerDTO modify(AdventureReviewAnswerDTO adventureAnswerDto, String content);
	 
	 public void delete(AdventureReviewAnswerDTO adventureAnswerDto);
}
