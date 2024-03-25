package com.example.test.User.DAO;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.User.DTO.UserDTO;

@Repository
public class UserDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(UserDTO userDto) {
		int result = this.sqlSessiontemplate.insert("user.insert", userDto);
		return result;
	}
	
	public int insertBis(UserDTO userdto) {
		int result = this.sqlSessiontemplate.insert("user.insertBis", userdto);
		return result;
	};

	public Map<String, Object> login(UserDTO userdto) {
		Map result = this.sqlSessiontemplate.selectOne("user.login", userdto);
		return result;
	}

	public int userUpdate(UserDTO userdto) {
		int result = this.sqlSessiontemplate.update("user.updateUser", userdto);
		return result;
	}
	
	public int BisUpdate(UserDTO userdto) {
		int result = this.sqlSessiontemplate.update("user.updateBis", userdto);
		return result;
	}

	public Map<String, Object> getInfo(Integer uID) {
		Map<String, Object> map = this.sqlSessiontemplate.selectOne("user.getInfo", uID);
		return map;
	}


	
//	
//	@Id @GeneratedValue
//	@Column(name = "User_ID")
//	private Integer id;
//	
//	@NotNull
//	@Column(name = "Mbti_ID", unique = true)
//	private Integer mbtiID;
//	
//	@NotNull
//	@Column(unique =true)
//	@Size(max=15)
//	private String userName;
//	
//	@NotNull
//	@Size(min=8, max=20)
//	@NotEmpty(message = "비밀번호는 필수입니다.")
//	private String password;
//	
//	@Column(unique = true)
//	private String PhoneNumber;
//	
//	//Rank구현 방안 고민 필요 회원등급 => 이게 필요할려면 누적금액 atrr  
//	//주말 회의 
//	private String Rank;
//	
//	@Email
//	private String mail;
//	
//	@OneToMany
//	private String cart;
//	
//	@OneToMany
//	private String history;
//	
//	
//	private boolean admin;
	
	
}
                                                        