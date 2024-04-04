package com.example.test.Adventure.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventure_ReviewDTO {

	
	private Integer adventureReviewID; //작성자 아이디
	
	private Integer adventureID; //어드벤쳐 아이디
	
	private Adventure_CategoryDTO adventure_categoryID;
	
	private double rating;
	
	private UserDTO user; // 작성자 
	
	private Integer userId;
	
	private String reviewTitle;
	
	private String content;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private Set<UserDTO> suggestion; // 추천수
	
	//조회수
	private int views;

    
	
}
