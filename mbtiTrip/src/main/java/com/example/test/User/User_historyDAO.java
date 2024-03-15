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
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;


@Repository
public class User_historyDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> userHistory) {
		int result = this.sqlSessiontemplate.insert("userHistroy.insert",userHistory);
		return result;
	}
	
	
//	@Id
//	@GeneratedValue
//	@Column(name = "UserHistory_ID")
//	private Integer id;
//	
//	
//	@Column(name = "Replace_ID")
//	private Integer replaceID;
//	
//	@Column(name = "Rc_ID")
//	private Integer replaceCategoryID;
//	
//	@Column(name = "Adventure_ID")
//	private Integer adventureID;
//	
//	@Column(name = "Post_ID")
//	private Integer postID;
//	
//	@Column(name = "Post_Category_ID")
//	private Integer postCategoryID;
//	
//	private LocalDateTime updateDate;
}
