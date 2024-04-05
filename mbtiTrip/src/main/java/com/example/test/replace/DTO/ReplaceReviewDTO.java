package com.example.test.replace.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.ReplaceReviewAnswer.ReplaceReviewAnswerDTO;
import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReplaceReviewDTO {

	private Integer replaceReivewID;
	
	private UserDTO userId;
	
	private String userName;

	private Integer replaceID;
	
	private Integer rating;
	
	private String reviewTitle;
	
	private String content;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private Set<UserDTO> suggestion; // 추천수
	
	private ReplaceCategoryDTO category;
	
	//조회수
	private int views;

	private List<ReplaceReviewAnswerDTO> answerList;
}
