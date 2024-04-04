package com.example.test.User.DTO;

import java.time.LocalDateTime;
import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartDTO {

	private Integer UserCartID;  //PK
	
	private String userName;  //Fk
	
	private Integer finalPrice;
	
	//UserCartRePlace
	private ReplaceDTO replaceInfo;
	
	//UserCartAdventrud
	private AdventureDTO adventureInfo;  //FK
	
	private LocalDateTime StartDate;

	private LocalDateTime endDate;

	private boolean payment;
	
}
