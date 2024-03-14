package com.example.test.replace;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Replace {

	@Id
	@GeneratedValue
	@Column(name = "Replace_ID")
	private Integer id;
	
	@Column(name = "Rc_ID")
	private Integer replaceCategoryId;
	
	@ManyToOne
	@Column(name = "Mbti_ID")
	private Integer mbtiID;
	
	@ManyToOne
	@Column(name = "City_ID")
	private Integer cityID;
	
	@ManyToOne
	@Column(name = "Post_Category_ID")
	private Integer Post_Category_ID;
	
	@NotNull
	private String R_name;
	
	@NotNull
	private String R_location;
	
	@NotNull
	private Integer R_price;
	
	@NotNull
	private String R_content;
	
	
	private String R_tel;
	
	@NotNull
	private String R_admin;
	
	
	private Integer R_rating_avg;
	
	
	@OneToMany(mappedBy = "rivew")
	@Column(name = "Reivew_ID")
	private String review;
	
}
