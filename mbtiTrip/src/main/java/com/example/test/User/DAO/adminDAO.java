package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class adminDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int userBaned(String userName){
		return this.sqlSessiontemplate.update("admin.userBaned", userName);
	}
	
	public int userReportCnt(String userName) {
		return this.sqlSessiontemplate.selectOne("admin.userReportCnt", userName);
	}
	
	public int userReport(String userName) {
		return this.sqlSessiontemplate.update("admin.report", userName);
	}

	public Map<String, Integer> userCount() {
		 Map<String, Integer> result = new HashMap();
		 int roleUser = this.sqlSessiontemplate.selectOne("admin.UserCnt");
		 int roleBis =  this.sqlSessiontemplate.selectOne("admin.BisCnt");
		 result.put("userCnt", roleUser);
		 result.put("BisCnt", roleBis);
		 result.put("ALLCnt", roleUser+roleBis);
		return result;
	}
	
}
