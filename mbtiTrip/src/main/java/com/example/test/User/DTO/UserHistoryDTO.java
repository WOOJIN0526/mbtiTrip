package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryDTO {
	
	private Integer UserHitoryID; //pk
	
	private Integer replace;   //fk
	
	private Integer replaceCategory;   //fk
	
	private Integer adventure;  //fk
	
	private Integer postID;  //fk
	
	private Integer postCategory;  //fk
	
	private LocalDateTime updateDate;
}
