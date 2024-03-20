package com.example.test.User.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
	
	private String UID;	//p
	
	private String userId;  
	
	private String userName; 
	
	private Integer mbti;   //FK
	
	private String password;

	private String phone;
	
	private String Rank;
	
	private String mail;
	
	private String cart;
	
	private String history;
	
	private User_Role userrole;
	
	@Override
	   public String toString() {
	      return "UserDTO [userId=" + userId + ", password=" + password + ", "
	         + "userName=" + userName + ", mail=" + mail
	         + ", phone=" + phone+ 
	         ", mbti="+mbti+"]"+ ", admin= "+ userrole;
	   }
	
	
}
