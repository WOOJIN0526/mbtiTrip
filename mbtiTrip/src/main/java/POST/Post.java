package POST;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post {

	@Id @GeneratedValue
	@Column(name= "Post_ID")
	private Integer Post_id;
	
	@Column(name= "Post_Category_ID")
	private Integer PC_Key;
	
	@Column(name= "USER_KEY")
	private Integer User_id;
	
	@Column(length = 100)
	private String title;
	

	private LocalDateTime updateDate;
	
	
	private LocalDateTime modifyDate;
	
	@Column
	private Integer views;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column
	private String suggestion;
	
	@Column
	private String unsuggestion;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	//@ManyToOne
	//private User author; user엔티티 마무리시 수정
	
	//@ManyToMany
	//Set<User> voter; user엔티티 마무리시 수정
}
