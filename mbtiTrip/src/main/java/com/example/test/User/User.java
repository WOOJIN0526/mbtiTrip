package com.example.test.User;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User {

	
	@Id @GeneratedValue
	@Column(name = "User_ID")
	private Integer id;
	
	@NotNull
	@Column(name = "Mbti_ID", unique = true)
	private Integer mbtiID;
	
	@NotNull
	@Column(unique =true)
	@Size(max=15)
	private String userName;
	
	@NotNull
	@Size(min=8, max=20)
	@NotEmpty(message = "비밀번호는 필수입니다.")
	private String password;
	
	@Column(unique = true)
	private String PhoneNumber;
	
	//Rank구현 방안 고민 필요 회원등급 => 이게 필요할려면 누적금액 atrr  
	//주말 회의 
	private String Rank;
	
	@Email
	private String mail;
	
	@OneToMany
	private String cart;
	
	@OneToMany
	private String history;
	
	
	private boolean admin;
	
}


/* 3.13 작업자 신성진*/