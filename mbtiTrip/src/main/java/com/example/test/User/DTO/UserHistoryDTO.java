package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryDTO {
	
	private String replace;
	
	private String replaceCategory;
	
	private String adventure;
	
	private String postID;
	
	private String postCategory;
	
	private LocalDateTime updateDate;
}
