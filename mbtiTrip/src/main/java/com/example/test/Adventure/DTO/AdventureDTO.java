package com.example.test.Adventure.DTO;



import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AdventureDTO {

	
	private Integer adventureID;
	
	private Integer adventureCategoryID;
	
	private Integer mbtiID;
	
	private Integer cityID;
	
	private Integer postCategoryID;
	
	private Integer adventureTypeId;
	
	private String adventureLocation;
	
	private String adventureName;
	
	private Integer adventurePrice;
	
	private String adventureContent;
	
	private String tel;
	
	private String adventureAdmin;
	
	private UserDTO Author;
	
	private double ratingAvg;
	
	private Set<UserDTO> voter;
	
	private Adventure_CategoryDTO adCategory;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private Integer views;
	
}
