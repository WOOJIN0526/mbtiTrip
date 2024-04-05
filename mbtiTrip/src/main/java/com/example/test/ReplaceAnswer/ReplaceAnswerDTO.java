package com.example.test.ReplaceAnswer;

import java.time.LocalDateTime;


import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceAnswerDTO {

	private Integer answerID;
	private ReplaceDTO replace;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
	
}
