package com.example.test.User;

import java.time.LocalDateTime;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Repository
public class User_Cart_ReplaceDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> userCartReplace) {
		int result = this.sqlSessiontemplate.insert("userCartReplace.insert",userCartReplace);
		return result;
	}
	
	
//	@Id
//	@GeneratedValue
//	@Column(name = "UCRE_ID")
//	private Integer id;
//
//	@OneToMany(mappedBy = "replace")
//	@Column(name = "Replace_ID")
//	private Integer replaceID;
//	
//	@Column(name = "UserCart_ID")
//	private Integer userCartID;
//	
//	
//	private LocalDateTime updateDate;
}
