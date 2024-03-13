package Adventure;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Adventure_Review {

	@Id @GeneratedValue
	@Column(name="Adventure_Review_Key")
	private Integer ar_id;
	
	@Column(name="Adventure_ID")
	private Integer adventure_id;
	
	@Column
	private long ar_rating;
	
	@Column
	private String userName;
	
	@Column
	private LocalDateTime ar_UpdateDate;
	
	
	
}
