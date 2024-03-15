package com.example.test.User.;

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
public class User_Cart_AdventureDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> userCartAdventure) {
		int result = this.sqlSessiontemplate.insert("userCartAdventure.insert",userCartAdventure);
		return result;
	}

	
	
}
