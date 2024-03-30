package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Integer answerID;
	
	private Integer postID;
	
	private Long ano;
	private Long pno;
	private String replay;
	private String replayer;
	private LocalDateTime replayDate;
	private LocalDateTime updateDate;
	
	
	
	
}
