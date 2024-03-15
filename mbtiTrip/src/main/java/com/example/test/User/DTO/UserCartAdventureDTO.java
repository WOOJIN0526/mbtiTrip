package com.example.test.User.DTO;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCartAdventureDTO {


	private Integer id;

	private Integer adventureID;
	

	private Integer userCartID;
	
	
	private LocalDateTime updateDate;
}
