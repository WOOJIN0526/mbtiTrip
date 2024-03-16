package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

	private Integer Post_id;
	
	private Integer PC_Key;
	
	private Integer User_id;
	
	private String title;
	
	private Integer views;
	
	private String content;
	
	private String suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private Integer answerList;
	
	
}
