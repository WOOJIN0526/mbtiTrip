package City;

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
public class city {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer city_id;
	
	@Column
	private String city;
	
	@Column
	private Integer replace_id;
	
	@Column
	private Integer adventure_id;
	
	@Column
	private Integer category_id;
}
// 시티 엔티티 컬럼세부조정필요 0312 김현석