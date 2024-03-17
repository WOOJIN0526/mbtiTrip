package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserHistoryDTO {
	
	private Integer UserHitoryID; //pk
	
	private Integer replaceID;   //fk
	
	private Integer replaceCategory;   //fk

	private Integer adventureID;  //fk
	
	private Integer postID;  //fk
	
	private Integer postCategoryID;  //fk
	
	private LocalDateTime updateDate;
}
