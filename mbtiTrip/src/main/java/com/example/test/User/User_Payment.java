package com.example.test.User;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User_Payment {

	
	@Id
	@GeneratedValue
	@Column(name = "UserPayment_ID")
	private Integer id;
	
	@OneToMany(mappedBy = "Cart")
	@Column(name = "UserCart_ID")
	private Integer userCartID;

	private String Card_info;
	
	private String Card_Num;
	
	private Integer price;
	
	private LocalDateTime paymentTime;
}
