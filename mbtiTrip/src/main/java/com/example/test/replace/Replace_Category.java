package com.example.test.replace;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Replace_Category {

	@Id
	@GeneratedValue
	@Column(name = "Rc_ID")
	private Integer id;
	
	private String Replace_Category;
	//분위기 등
}
