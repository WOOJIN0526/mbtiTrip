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
import mbti.mbti;

@Getter
@Setter
@Entity
public class post_answer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column
	private LocalDateTime createDate;

	@ManyToOne  
	private post_question question; 
	
	@ManyToOne
	private user.SiteUser author; //작성자
	
	@Column
	private LocalDateTime modifyDate;
}
