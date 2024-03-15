package com.example.test.POST;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

	@Id @GeneratedValue
	@Column(name="Answer_Key")
	private Integer answer_id;
	
	@Column(name="Post_ID")
	private Integer Post_id;
	
	
	@Column(columnDefinition = "TEXT")
	@NotEmpty(message = "내용은 필수항목입니다.")
	private String content;
	
	@ManyToOne
	private Post post; //Answer <-> Post
	
	//@ManyToOne
	//private User author; user 엔티티부분 마무리시 수정
	
	//@ManyToMany
	//Set<User> voter; user 엔티티부분 마무리시 수정
	
	
	private LocalDateTime answer_updateDate;
	
	
	private LocalDateTime answer_modifiDate;
}
