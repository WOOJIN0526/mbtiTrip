package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

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
	
	private UserDTO author;
	
	//private String suggestion; 나중에 userDTO에서 Set으로 voter?
	private Set<UserDTO> voter;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private List<AnswerDTO> answerList;
	
	private Post_CategoryDTO post_category;
	
	
	
}
