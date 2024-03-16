package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartDTO {


	private Integer id;  //PK
	
	private String author;  //Fk
	
	private Integer price;
	
	
	private String ucreID; //FK
	//UserCartRePlace
	
	private String ucadID;  //FK
	//UserCartAdventrud
	
	private LocalDateTime updateDate;
}
