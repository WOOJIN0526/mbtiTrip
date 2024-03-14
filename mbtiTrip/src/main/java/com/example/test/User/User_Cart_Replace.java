package com.example.test.User;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User_Cart_Replace {

	
	@Id
	@GeneratedValue
	@Column(name = "UCRE_ID")
	private Integer id;

	@OneToMany(mappedBy = "replace")
	@Column(name = "Replace_ID")
	private Integer replaceID;
	
	@Column(name = "UserCart_ID")
	private Integer userCartID;
	
	
	private LocalDateTime updateDate;
}
