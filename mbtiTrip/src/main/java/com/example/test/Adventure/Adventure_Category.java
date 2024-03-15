package com.example.test.Adventure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Adventure_Category {

	@Id @GeneratedValue
	@Column(name="Adventure_Category")
	private Integer AC_id;
	
	@Column
	private String ACategory;
}
