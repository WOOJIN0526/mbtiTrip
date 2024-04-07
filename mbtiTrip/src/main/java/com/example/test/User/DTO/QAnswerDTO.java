package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QAnswerDTO {

	private Integer AID;
	
	private Integer QID;
	
	private String adminName;
	
	private String A_content;
	
	private LocalDateTime AupdateDate;
	
	
}
