package Concept_Category;

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
public class concept_category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer concept_category_id;
	
	@Column
	private String concept;
	
	
}
// 컨셉카테고리 엔티티 0312 김현석