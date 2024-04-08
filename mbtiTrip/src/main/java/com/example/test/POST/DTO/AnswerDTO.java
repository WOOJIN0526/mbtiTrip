package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO {

	private Integer answerID;
	private PostDTO postID;
	private PostReviewDTO prID;
	private ItemDTO itemID;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
	
	
	
	
}
