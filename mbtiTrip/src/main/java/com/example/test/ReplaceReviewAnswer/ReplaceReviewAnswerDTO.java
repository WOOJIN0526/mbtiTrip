package com.example.test.ReplaceReviewAnswer;

import java.time.LocalDateTime;

import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceReviewAnswerDTO {

	private Integer answerID;
	private ReplaceReviewDTO replaceReview;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
}
