package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;



import com.example.test.User.DTO.UserDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postID;
	
	private Integer postCategoryID;
	
	private Integer userId;
	
	@Column(length = 200)
	private String title;
	
	private Integer views;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	//private String suggestion; 나중에 userDTO에서 Set으로 voter?
	private Set<UserDTO> voter;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<AnswerDTO> answerList;
	
	@ManyToOne
	private UserDTO author;
}
