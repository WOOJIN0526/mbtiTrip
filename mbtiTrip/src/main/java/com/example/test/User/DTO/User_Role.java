package com.example.test.User.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public enum User_Role {

	admin("ROLE_ADMIN"),
	bis("ROLE_BIS"),
	user("ROLE_USER");
	
	private String value;
}
