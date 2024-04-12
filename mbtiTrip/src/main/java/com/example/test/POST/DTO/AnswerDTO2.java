package com.example.test.POST.DTO;

import java.time.LocalDateTime;

import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO2 {
	private Integer postId;
	private Integer answerID;
	private PostReviewDTO prID;
	private ItemDTO itemID;
	private String content;
	private LocalDateTime updateDate;
	private LocalDateTime modifyDate;
	private UserDTO writer;
}
