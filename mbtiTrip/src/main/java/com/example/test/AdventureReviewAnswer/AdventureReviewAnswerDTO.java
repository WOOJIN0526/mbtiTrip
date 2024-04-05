package com.example.test.AdventureReviewAnswer;

import java.time.LocalDateTime;


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdventureReviewAnswerDTO {

	private Integer answerID;
	private Adventure_ReviewDTO adventureReview;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
}
