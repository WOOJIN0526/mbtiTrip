package com.example.test.item.DTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.User.DTO.AdminDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.ItemType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {

	private Integer itemID;
	
	private ItemType Type;
	
	private Integer mbti;
	
	private UserDTO Username; //admin과 동일
	
	private Integer price;

	private String itemName;
	
	private String location;
	
	private String tel;
	
	private String contents;
	
	private Integer view;
	
	private double ratingAvg = 0;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime updateDate;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime modifyDate;
	
	private Integer uprating; //추천수 

	private MultipartFile[] file;
	
	private String[] ImgeUrl = null; //사진 저장 Xml 쿼리 작성시 아마 따로 불러와서 set 하던지 해야 함 

	@Override
	public String toString() {
		return "ItemDTO [itemID=" + itemID + ", Type=" + Type + ", mbti=" + mbti + ", Username=" + Username + ", price="
				+ price + ", itemName=" + itemName + ", location=" + location + ", tel=" + tel + ", contents="
				+ contents + ", ratingAvg=" + ratingAvg + ", updateDate=" + updateDate + ", uprating=" + uprating
				+ ", ImgeUrl=" + Arrays.toString(ImgeUrl) + "]";
	}
}


