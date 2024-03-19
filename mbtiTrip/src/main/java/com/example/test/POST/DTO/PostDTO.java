package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PostDTO {

	private Integer postID;
	
	private Integer postCategoryID;
	
	private Integer userId;
	
	private String title;
	
	private Integer views;
	
	private String content;
	
	private String suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	

	
	
}
