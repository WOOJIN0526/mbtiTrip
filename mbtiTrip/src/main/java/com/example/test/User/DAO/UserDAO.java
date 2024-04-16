package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.User.DTO.UserDTO;
import com.example.testExcepion.SignUP.SignUpException;
import com.example.testExcepion.SignUP.SignUpExceptionEunm;

import groovyjarjarasm.asm.tree.TryCatchBlockNode;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class UserDAO {


	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	


	public int insert(UserDTO userDto) {
		int result = 0 ;
		try {
			result = this.sqlSessiontemplate.insert("user.insert", userDto);
		} catch (Exception e) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_INTERNAL_ERROR);
		}
		
		return result;
	
	}
	
	public int insertBis(UserDTO userdto) {
		int result = 0 ;
		try {
			result = this.sqlSessiontemplate.insert("user.insertBis", userdto);
		} catch (Exception e) {
			throw new SignUpException(SignUpExceptionEunm.SIGN_INTERNAL_ERROR);
		}
		
		return result;
	};


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

	public Integer getUID(String userName) {
		Integer UID = this.sqlSessiontemplate.selectOne("user.getUID", userName);
		return UID;
	}



	public Map<String, Object> login(UserDTO userdto) {
		Map result = this.sqlSessiontemplate.selectOne("user.login", userdto);
		return result;
	}
	
	public UserDTO getByUserId(String userId) {
		log.info("userID  ==> {}", userId);
		log.info(sqlSessiontemplate.getClass());
		UserDTO result = this.sqlSessiontemplate.selectOne("user.getbyuserId", userId);
		log.info("userDTO  ==> {}", result);
		
		return result;
	}

	public UserDTO findByUsername(String name) {
		// TODO Auto-generated method stub 
		
		UserDTO user = this.sqlSessiontemplate.selectOne("user.getUsername", name);
	
		return user;
	}

	public List<HashMap<String, Object>> getMyItem(String userName) {
		log.info("getMyItem userName = > {}" , userName);
		List<HashMap<String, Object>> myItem = this.sqlSessiontemplate.selectList("user.getMyItem", userName); 
		return myItem;
	}
	
	/**/
	public String getUserNameByuserID(String userId) {
		String userName = this.sqlSessiontemplate.selectOne("getUserNamebyuserId", userId);
		return userName;
	}
	
	
	public boolean idCk(UserDTO userdto) {
		boolean ck = false;
		
		int idCk = this.sqlSessiontemplate.selectOne("user.ckUserID", userdto);
		if(idCk != 0) {
			ck = true;
		}
		return ck;
	}
	
	public boolean nameCk(UserDTO userdto) {
		boolean ck = false;
		int nameCk = this.sqlSessiontemplate.selectOne("user.ckUsername", userdto);
		if(nameCk != 0) {
			ck = true;
		}
		return ck;
	}
	
	public boolean mailCK(UserDTO userdto) {
		boolean ck = false;
		int mailCk = this.sqlSessiontemplate.selectOne("user.ckmail", userdto);
		if(mailCk != 0) {
			ck = true;
		}
		return ck;
	}


	
}
                                                        