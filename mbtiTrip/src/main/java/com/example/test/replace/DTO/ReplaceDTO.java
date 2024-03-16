package com.example.test.replace.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceDTO {


	private Integer id;    //pk
	
	private Integer replaceCategoryId;   //fk
	
	private Integer mbtiID;    //fk

	private Integer cityID;    //fk
	
	private Integer Post_Category_ID;    //fk
	 
	private String R_name;
	
	private String R_location;
	
	private Integer R_price;

	private String R_content;
	
	private String R_tel;
	
	private String R_admin;
	
	private Integer R_rating_avg;
	
	private String review;
}
