package com.example.test.replace.DTO;

import java.time.LocalDateTime;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReplaceReviewDTO {

	private Integer replaceReivewID;
	
	private Integer userId;
	
	private String userName;

	private Integer replaceID;
	
	private Integer rating;
	
	private String reviewTitle;
	
	private String content;

	private String writer;
	
	private LocalDateTime regDate;
	
	private LocalDateTime modifyDate;
	
	private Long pno;
	
	//댓글수처리위함
	private int answerCnt;
	
	//조회수
	private int viewCount;

    public void addViewCount() {
        this.viewCount++;
    }
}
