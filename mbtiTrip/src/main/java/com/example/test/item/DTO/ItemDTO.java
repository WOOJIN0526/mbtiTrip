package com.example.test.item.DTO;

import java.time.LocalDateTime;
import java.util.Arrays;

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
	
	private Integer view;
	
	private double ratingAvg = 0;
	
	private LocalDateTime updateDate;
	

	private Integer uprating = 0; //추천수 

	
	private String[] ImgeUrl = null; //사진 저장 Xml 쿼리 작성시 아마 따로 불러와서 set 하던지 해야 함 

	@Override
	public String toString() {
		return "ItemDTO [itemID=" + itemID + ", Type=" + Type + ", mbti=" + mbti + ", Username=" + Username + ", price="
				+ price + ", itemName=" + itemName + ", location=" + location + ", tel=" + tel + ", contents="
				+ contents + ", ratingAvg=" + ratingAvg + ", updateDate=" + updateDate + ", uprating=" + uprating
				+ ", ImgeUrl=" + Arrays.toString(ImgeUrl) + "]";
	}
}


