package com.example.test.Adventure.DTO;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventure_ReviewDTO {

	
	private Integer ar_id;
	
	private Integer adventure_id;
	
	private long ar_rating;
	
	private String userName;
	
	private LocalDateTime ar_UpdateDate;
	
	
	
}
