package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User_PaymentDTO {
	
	private Integer id;
	
	private Integer userCartID;

	private String Card_info;
	
	private String Card_Num;
	
	private Integer price;
	
	private LocalDateTime paymentTime;
}
