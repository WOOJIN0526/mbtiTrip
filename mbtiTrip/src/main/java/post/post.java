package post;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id; 
	
	@Column(unique = true) //중복허용 x
	private String username;
	
	@Column(unique = true)
	private Integer post_category_id;
	
	@Column(unique = true)
	private String mbti;
	
	@ManyToOne
	private user.SiteUser author;
	
	private String title;
	
	private String views;
	
	private String contents;
	
	private String answerList;
	
	private String suggestion;
	
	private String unsuggestion;
	
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
}
