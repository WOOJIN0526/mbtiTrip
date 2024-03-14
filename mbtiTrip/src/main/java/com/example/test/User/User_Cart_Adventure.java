package com.example.test.User;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User_Cart_Adventure {

	@Id
	@GeneratedValue
	@Column(name = "UCAD_ID")
	private Integer id;
	
	@Column(name ="Adventure_ID")
	private Integer adventureID;
	
	@Column(name = "UserCart_ID")
	private Integer userCartID;
	
	
	private LocalDateTime updateDate;
	
	
}
