package com.example.test.AdventureAnswer;

import java.time.LocalDateTime;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdventureAnswerDTO {

	private Integer answerID;
	private AdventureDTO adventure;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
}
