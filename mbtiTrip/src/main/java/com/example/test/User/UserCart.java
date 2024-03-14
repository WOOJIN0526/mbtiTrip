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
public class UserCart {

	@Id
	@GeneratedValue
	@Column(name = "UserCart_ID")
	private Integer id;
	
	private String author;
	
	private Integer price;
	
	
	@Column(name = "UCRE_ID")
	private Integer ucreID;
	//UserCartRePlace
	
	
	@Column(name = "UCAD_ID")
	private Integer ucadID;
	//UserCartAdventrud
	
	private LocalDateTime updateDate;
}

