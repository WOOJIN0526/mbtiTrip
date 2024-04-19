package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class adminDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int userBaned(String userName){
		int result =this.sqlSessiontemplate.update("admin.userBaned", userName);
		return result;
	}
	public int userUnblock(String userName){
		int result =this.sqlSessiontemplate.update("admin.userUnBlock", userName);
		return result;
	}
	
	public int userReportCnt(String userName) {
		int result =this.sqlSessiontemplate.selectOne("admin.userReportCnt", userName);
		return result;
	}
	
	public int userReport(String userName) {
		int result =this.sqlSessiontemplate.update("admin.report", userName);
		return result;
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
	
	public List<HashMap<String, Object>> userList(){
		List<HashMap<String, Object>> userList = this.sqlSessiontemplate.selectList("admin.userList");
		return userList;
	}
	public List<HashMap<String, Object>> bisList(){
		List<HashMap<String, Object>> bisList = this.sqlSessiontemplate.selectList("admin.bisList");
		return bisList;
	}
	
	
	public List<HashMap<String, Object>> mbtiCnt(){
		List<HashMap<String, Object>> mbtiCnt = this.sqlSessiontemplate.selectList("admin.mbtiCnt");
		return mbtiCnt;
	}
}
