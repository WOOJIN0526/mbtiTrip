package user;

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
public class SiteUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer user_id; //유저 아이디
	
	@Column(unique = true) //중복허용 x
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String mbti;
	
	@Column(unique = true)
	private String PNumber;
	
	@Column(unique = true)
	private String email;
	
	
	
}
