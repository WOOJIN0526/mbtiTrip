package mbti;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class mbti {
	// mbti
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "mbti_list")
	private String mbti;
}
/* 3월 8일 작업자 신성진
 * mbti ENtitiy 생성 
 * 현재 오류 없음
 */
 