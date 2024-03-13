package POST;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Post_Category {

	@Id @GeneratedValue
	@Column(name="Post_Category_ID")
	private Integer PC_Key;
	
	@Column
	private String category;
	
}
