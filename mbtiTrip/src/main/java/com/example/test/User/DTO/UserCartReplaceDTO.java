package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartReplaceDTO {

	private Integer UserCartReplaceID; //pk


	private Integer replaceID; //fk
	

	private Integer userCartID; //fk
	
	
	private LocalDateTime updateDate;
}
