package com.example.test.POST.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Integer answerID;
	
	private Integer postID;
	
	private String content;
	
	private PostDTO post; //Answer <-> Post	
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifiDate;
}
