package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Shinsungjin
 * 결제시 사용할 클래스이나, 가용성이 떨어져 사용하지 않습니다. 
 * @deprecated
 * */


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
