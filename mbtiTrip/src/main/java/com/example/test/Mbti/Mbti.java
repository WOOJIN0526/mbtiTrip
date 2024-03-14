package com.example.test.Mbti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mbti {
	
	@Id
	@GeneratedValue
	@Column(name = "Mbti_ID")
	private Integer id;
	
	@Column(unique = true)
	private String mbti;
	
	private String mbti_ex;

}


/* 3.13 작업자 신성진*/