package com.example.test.Adventure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Adventure {

	@Id @GeneratedValue
	@Column(name="Adventure_ID")
	private Integer adventure_id;
	
	@Column(name="Replace_Category")
	private Integer AR_id;
	
	@Column(name="MBTI_ID")
	private Integer mbtikey;
	
	@Column(name="City_key")
	private Integer City_id;
	
	@Column(name="Post_Category_ID")
	private Integer PC_Key;
	
	@Column(name="Ad_Type_Key")
	private Integer Type_RE_id;
	
	@Column
	private String A_Location;
	
	@Column
	private String A_Name;
	
	@Column
	private long A_price;
	
	@Column(columnDefinition = "TEXT")
	private String A_Content;
	
	@Column
	private String A_Tel;
	
	@Column
	private String A_Admin;
	
	@Column
	private double A_Rating;
	
}
