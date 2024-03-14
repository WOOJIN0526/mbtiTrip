package com.example.test.replace;

import java.time.LocalDateTime;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class R_Reivew {

	@Id
	@GeneratedValue
	@Column(name = "Reivew_ID")
	private Integer id;
	
	@ManyToOne
	@Column(name = "Replace_ID")
	private Integer replaceID;
	
	@NotNull
	private Integer rating;
	
	@NotNull
	private String content;

	private LocalDateTime updateDate;

	private LocalDateTime modifyDate;
}
