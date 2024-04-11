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

	private Integer itemID;
	
	private Post_CategoryDTO category;
	
	private String title;
	
	private String content;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private UserDTO writer;
	
	private int views;
	
	private double ratingAvg;
	
	private Set<UserDTO> suggestion;

	@Override
	public String toString() {
		return "PostReviewDTO [itemID=" + itemID + ", category=" + category + ", title=" + title + ", content="
				+ content + ", updateDate=" + updateDate + ", modifyDate=" + modifyDate + ", writer=" + writer
				+ ", views=" + views + ", suggestion=" + suggestion + "]";
	}

	

	
}
