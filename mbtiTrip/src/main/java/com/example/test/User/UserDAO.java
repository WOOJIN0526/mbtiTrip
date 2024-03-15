package com.example.test.User;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> user) {
		int result = sqlSessiontemplate.insert("user.insert", user);
		return result;
	};
	
	
	
}
                                                        