package post;

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
public class post_category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer post_category_id;
	
	@Column
	private String post_categoryName;
	
	@Column
	private Integer city_id;
	
	@Column
	private Integer mbti_id;
	
	@Column
	private Integer Trip_id;
	
}
// 포스트 카테고리 엔티티 아이디값들 컬럼세부조정필요? 0312 김현석