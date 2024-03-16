package com.example.test.POST.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Integer answer_id;
	
	private Integer Post_id;
	
	private String content;
	
	private PostDTO post; //Answer <-> Post
	
	//@ManyToOne
	//private User author; user 엔티티부분 마무리시 수정
	
	//@ManyToMany
	//Set<User> voter; user 엔티티부분 마무리시 수정
	
	
	
	private LocalDateTime answer_updateDate;
	
	private LocalDateTime answer_modifiDate;
}