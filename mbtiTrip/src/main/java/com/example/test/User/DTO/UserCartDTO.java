package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartDTO {


	private Integer UserCartID;  //PK
	
	private String userID;  //Fk
	
	private Integer price;
	
	private Integer ucreID; //FK
	//UserCartRePlace
	
	private String ucadID;  //FK
	//UserCartAdventrud
	
	private LocalDateTime updateDate;
}
