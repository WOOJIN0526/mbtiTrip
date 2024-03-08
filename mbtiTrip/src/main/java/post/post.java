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
public class post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id; 
	
	@Column(unique = true) //중복허용 x
	private String username;
	
	@Column(unique = true)
	private Integer post_category_id;
	
	@Column
	private mbti mbti;
	
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
// 몇몇 속성은 어디서 쓰일지 모르지만 일단 집어넣어둠 0308 김현석 수정