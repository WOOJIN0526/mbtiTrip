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
	
	private String content;
	
	private String writer;
	
	//private String suggestion; 나중에 userDTO에서 Set으로 voter?
	//private Set<UserDTO> voter;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modifyDate;
	
	//private List<AnswerDTO> answerList;
	
	//private Post_CategoryDTO post_category;
	
	private Long pno;
	
	//댓글수처리위함
	private int answerCnt;
	
	//조회수
	private int viewCount;

    public void addViewCount() {
        this.viewCount++;
    }
	
}
