package com.example.test.User.DTO;

import java.beans.BeanProperty;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter 
public class AdminDTO {

	private Integer adminId;
	
	private int rating;
	
	
	private List<String> dailyRating = new ArrayList();
}
