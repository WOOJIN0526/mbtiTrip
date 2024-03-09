package RePlace;

import java.util.List;

import Reveiw.Review;
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
public class Replace {
	/*3월 8일 작업자 신성진 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer replace_id;
	
	@Column
	private String replace;
	
	@Column
	private String location;
	
	@ManyToOne
	private mbti recommand_mbti;
	
	private String price;
	
//	@ManyToOne
//	private Category category_id;
	
	@Column
	private String contents;
	
	@Column
	private String tel;
	
	@Column
	private String replace_admin;
	
	@OneToMany
	private List<Review> reivewList;
	//추후 마이바티스 도입시 키 값으로 변경 해야 할 듯 
	
	
	
}
