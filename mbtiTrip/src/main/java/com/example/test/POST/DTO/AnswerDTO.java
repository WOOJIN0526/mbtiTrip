package com.example.test.POST.DTO;

import java.time.LocalDateTime;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Integer answerID;
	
	private Integer postID;
	
	private String content;
	
	private PostDTO post; //Answer <-> Post	
	
	private UserDTO Author;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifiDate;
	
	private int replyNo;
	private int postNo;
}
