package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PostDTO {

	private Integer postID;
	
	private Integer postCategoryID;
	
	private Post_CategoryDTO post_category;
	
	private Integer userId;
	
	private String title;
	
	private String content;
	
	private Set<UserDTO> suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	//private List<AnswerDTO> answerList;
	
	//private Post_CategoryDTO post_category;
	
	private UserDTO write;
	
	//조회수
	private int views;

    public void addViewCount() {
        this.views++;
    }
	
}
