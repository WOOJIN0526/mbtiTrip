package com.example.test.Adventure.DTO;

import java.time.LocalDateTime;

import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventure_ReviewDTO {

	
	private Integer adventureReviewID; //작성자 아이디
	
	private Integer adventureID; //어드벤쳐 아이디
	
	private double rating;
	
	private String userName; // 작성자 이름
	
	private Integer userId;
	
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
