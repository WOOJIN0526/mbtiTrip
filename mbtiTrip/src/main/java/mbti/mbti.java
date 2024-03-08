package mbti;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
/*3월 8일 작업자 신성진 */

@Getter
@Setter
@Entity
public class mbti {
	// mbti
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mbti_id;
	
	@Column(name = "mbti_list")
	private mbti mbti;
}
/* 3월 8일 작업자 신성진
 * mbti ENtitiy 생성 
 * 현재 오류 없음
 */
 