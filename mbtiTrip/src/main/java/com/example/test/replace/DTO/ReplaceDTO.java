package com.example.test.replace.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceDTO {


	private Integer replaceID;    //pk
	
	private Integer replaceCategoryID;   //fk
	
	private Integer mbtiID;    //fk

	private Integer cityID;    //fk
	
	private Integer postCategoryID;    //fk
	
	private Integer replaceType;  //fk
	 
	private String replaceName;
	
	private String replaceLocation;
	
	private Integer replacePrice;

	private String replaceContents;
	
	private String tel;
	
	private String replaceAdmin;
	
	private Integer R_rating_avg;
	
	private String review;
}
