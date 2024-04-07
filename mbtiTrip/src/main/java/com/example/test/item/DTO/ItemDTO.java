package com.example.test.item.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

	private Integer itemID;
	
	private String Type;
	
	private Integer mbti;
	
	private String Username; //admin과 동일
	
	private Integer price;

	private String itemName;
	
	private String location;
	
	private String tel;
	
	private String contents;
	
	private double ratingAvg;
	
	private LocalDateTime updateDate;
	
	private Integer upratring; //추천수 
	
	private String[] ImgeUrl; //사진 저장 Xml 쿼리 작성시 아마 따로 불러와서 set 하던지 해야 함 
}
