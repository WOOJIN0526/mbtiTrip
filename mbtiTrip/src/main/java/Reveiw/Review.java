package Reveiw;

import java.time.LocalDateTime;

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
public class Review {
	/*3월 8일 작업자 신성진 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer review_id;
	
	@Column
	private Integer rating;
	
	@Column
	private String review_content;
	
	@Column
	private String user_name;
	//그 user에서 id 불러와서 저장 하면 될 듯 
	// 해당 컬럼 추후 수정 필요 
	
	@Column
	private LocalDateTime createDate;
	
	private LocalDateTime modifyDate;
	
}
