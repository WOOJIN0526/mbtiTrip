package com.example.test.User.DTO;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class AdminDTO {

	private Integer adminId;
	
	private int rating;

	private List<String> dailyRating = new ArrayList();
	
	
}
