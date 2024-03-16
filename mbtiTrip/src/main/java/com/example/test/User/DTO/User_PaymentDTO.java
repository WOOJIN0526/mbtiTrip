package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User_PaymentDTO {
	
	private Integer userPaymentID;  //pk
	
	private Integer userCartID;   //fk

	private String CardInfo;   
	
	private String CardNum;   
	
	private Integer price;
	
	private LocalDateTime paymentTime;
}
