package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostReviewDTO {

	private Integer postReviewId;
	
	private Integer itemID;
	
	private Integer postCategoryID;
	
	private Post_CategoryDTO category;
	
	private String title;
	
	private String content;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private String writer;
	
	private int views;
	
	private double ratingAvg;
	
	private Set<UserDTO> suggestion;

	@Override
	public String toString() {
		return "PostReviewDTO [postReviewId=" + postReviewId + ", itemID=" + itemID + ", postCategoryID="
				+ postCategoryID + ", category=" + category + ", title=" + title + ", content=" + content
				+ ", updateDate=" + updateDate + ", modifyDate=" + modifyDate + ", writer=" + writer + ", views="
				+ views + ", ratingAvg=" + ratingAvg + ", suggestion=" + suggestion + "]";
	}

	

	

	
}
