package com.example.test.replace.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReplaceReviewDTO {

	private Integer replaceReivewID;
	
	private Integer userID;
	
	private String userName;

	private Integer replaceID;
	
	private Integer rating;
	
	private String content;

	private LocalDateTime updateDate;

	private LocalDateTime modifyDate;
}
