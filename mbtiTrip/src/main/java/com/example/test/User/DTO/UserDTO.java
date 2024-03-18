package com.example.test.User.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private String userID;  //p
	
	private String userName; 
	
	private String mbti;   //FK
	
	private String password;

	private String PhoneNumber;
	
	private String Rank;
	
	private String mail;
	
	private String cart;
	
	private String history;
	
	private boolean admin;
}