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
public class Replace_Type {

	@Id
	@GeneratedValue
	@Column(name = "RE_TypeID")
	private Integer id;
	
	
	private String Replace_Type_name;
	
}
