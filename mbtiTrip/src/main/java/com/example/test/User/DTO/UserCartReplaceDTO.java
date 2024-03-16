package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartReplaceDTO {

	private Integer id;


	private Integer replaceID;
	

	private Integer userCartID;
	
	
	private LocalDateTime updateDate;
}
