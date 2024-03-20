package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Answer {

private Integer answerID;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@ManyToOne
	private PostDTO post; //Answer <-> Post	
	
	@ManyToOne
    private UserDTO author;
	
	@ManyToMany
	Set<UserDTO> voter;
	 
	private LocalDateTime updateDate;
	
	private LocalDateTime modifiDate;
}
