package com.example.test.User.DTO;

import java.time.LocalDateTime;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserHistoryDTO {

	private String userName;
	
	private Integer postid;

	private Integer itemId;
	
	private LocalDateTime viewDate;

	@Override
	public String toString() {
		return "UserHistoryDTO [userName=" + userName + ", postid=" + postid + ", itemId=" + itemId + ", viewDate="
				+ viewDate + "]";
	}
	
	


}

