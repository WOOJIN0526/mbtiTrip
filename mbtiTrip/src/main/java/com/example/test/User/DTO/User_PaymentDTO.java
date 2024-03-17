package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User_PaymentDTO {
	
	private Integer userpaymentID;  //pk
	
	private Integer userCartID;   //fk

	private String cardInfo;   
	
	private String cardNum;   
	
	private Integer price;
	
	private LocalDateTime paymentTime;
}
