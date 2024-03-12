package post;

import java.time.LocalDateTime;
import java.util.List;

import groovy.transform.builder.Builder;
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
import mbti.mbti;

@Getter
@Setter
@Entity
public class post_question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id;
	
	@Column(length = 100)
	private String subject; // 제목
	
	@Column
	private String post_category; //카테고리

	@Column(columnDefinition = "TEXT")
	private String content; // 내용
	
	@Column(length=4)
	private mbti mbti; //mbti
	
	@Column
	private Integer views; //조회수
	
	private LocalDateTime createDate; // 작성일자
	
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<post_answer> answerList;
	
	@ManyToOne
	private user.SiteUser author; //작성자
	
	private LocalDateTime modifyDate;//수정일자
	

}
// 질문 엔티티
// 조회수 속성 추가 0312 김현석