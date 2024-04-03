package com.example.test.replace.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceDTO {


	private Integer replaceID;    //pk
	
	private ReplaceCategoryDTO replaceCategoryID;   //fk
	
	private Integer mbtiID;    //fk

	private Integer cityID;    //fk
	
	private Integer postCategoryID;    //fk
	
	private Integer replaceType;  //fk
	
	private Integer userId;
	 
	private String replaceName;
	
	private String replaceLocation;
	
	private Integer replacePrice;

	private String replaceContents;
	
	private String tel;
	
	private UserDTO replaceAdmin;
	
	private Integer R_rating_avg;
	
	private Set<UserDTO> suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	//조회수
	private int views;

 
}
