package Adventure;

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
public class adventure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer adventure_id;
	
	@Column
	private Integer category_id;
	
	@Column
	private String location;
	
	@Column
	private String recommand_mbti;
	
	@Column
	private Integer price;
	
	@Column
	private String contents;
	
	@Column
	private String tel;
	
	@Column
	private String adventure_admin;
	
	@Column
	private String rating;
	
	@Column
	private String review;
}
// 어드밴처 엔티티-컬럼세부 조정필요 0312 김현석