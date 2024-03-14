package com.example.test.User;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class User_history {

	
	@Id
	@GeneratedValue
	@Column(name = "UserHistory_ID")
	private Integer id;
	
	
	@Column(name = "Replace_ID")
	private Integer replaceID;
	
	@Column(name = "Rc_ID")
	private Integer replaceCategoryID;
	
	@Column(name = "Adventure_ID")
	private Integer adventureID;
	
	@Column(name = "Post_ID")
	private Integer postID;
	
	@Column(name = "Post_Category_ID")
	private Integer postCategoryID;
	
	private LocalDateTime updateDate;
}
