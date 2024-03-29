package com.example.test.User.DAO;

import java.time.LocalDateTime;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Repository
public class UserCartDAO {
//
//	@Id
//	@GeneratedValue
//	@Column(name = "UserCart_ID")
//	private Integer id;
//	
//	private String author;
//	
//	private Integer price;
//	
//	
//	@Column(name = "UCRE_ID")
//	private Integer ucreID;
//	//UserCartRePlace
//	
//	
//	@Column(name = "UCAD_ID")
//	private Integer ucadID;
//	//UserCartAdventrud
//	
//	private LocalDateTime updateDate;
	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> userCart) {
		int result = this.sqlSessiontemplate.insert("userCart.insert", userCart);
		return result;
	};
}

