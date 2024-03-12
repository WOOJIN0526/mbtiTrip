package Trip;

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
public class trip {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trip_id;
	
	@Column
	private String userName;
	
	@Column(length = 100)
	private String tripTitle; 
	
	@Column
	private LocalDateTime updateDate;
	
	@Column
	private LocalDateTime modifyDate;
	
	
}
