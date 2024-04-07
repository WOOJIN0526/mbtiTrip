package com.example.test.User.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class UserHistoryDAO {

	@Autowired
	SqlSessionTemplate sqlsessiontemplate;
	
	/*사용자가 해당 게시글들을 조회할 때마다 기록 */
	public void  viewCkItem(UserHistoryDTO userHistory) {
		this.sqlsessiontemplate.insert("view.viewRE", userHistory);
	}
	
	public void viewCkPO(UserHistoryDTO userHistoryDTO) {
		this.sqlsessiontemplate.insert("view.viewPO", userHistoryDTO);
	}
	
	
	
	//사용자가 조회한 숙소 정보 -> limit 통해서 최근 5개 건만
	public List<ItemDTO> viewReturnRE(String userName) {
		log.info("HIStoryDAO => {}", userName);
		List<ItemDTO> returnvalue =this.sqlsessiontemplate.selectList("view.ReturnRE", userName);
		return returnvalue;
	}
	
	//사용자가 조회한 놀거리 정보 -> limit 통해서 최근 5개 건만
	public List<ItemDTO> viewReturnAD(String userName) {
		log.info("HIStoryDAO => {}", userName);
		return this.sqlsessiontemplate.selectList("view.ReturnAD", userName);
	}
	
	//사용자가 조회한 게시글 정보 -> limit 통해서 최근 5개 건만
	public List<PostDTO> viewReturnPO(String userName) {
		log.info("HIStoryDAO => {}", userName);
		return this.sqlsessiontemplate.selectList("view.ReturnPO", userName);
	}

	
	//사용자가 작성한 개시물 
	public List<HashMap<String, Object>> userCreatePost(String userName){
		return this.sqlsessiontemplate.selectList("view.userCreatePost", userName);
	}
	
	public List<HashMap<String, Object>> userCreateQnA(String userName){
		return this.sqlsessiontemplate.selectList("view.userCreateQnA", userName);
	}
	
	
	
	/* UX를 위한 DB 정제, 사용자가 가장 많이 조회한 MBTI type*/
	public List<HashMap<String, Object>> uxMbti(String userName){
		log.info("UserName --->{}", userName);
		return this.sqlsessiontemplate.selectList("view.userLikeMbti", userName);
	}
	
	/*이하 위 기능을 활용하여 각각 ALL, adventure, replace 정보 추천 최대 4개 */
	//아래 메소드는 루트 추천을 위해 각각 replace정보와 adventure 정보를 담고 있음 
	public List<HashMap<String, Object>> rutinByUx(String mbti){
		return this.sqlsessiontemplate.selectList("view.rutinALL", mbti);
	}

	public List<HashMap<String, Object>> rutinADByUx(String mbti) {
		return this.sqlsessiontemplate.selectList("view.rutinAD", mbti);
		
	}
	
	public List<HashMap<String, Object>> rutinREByUx(String mbti) {
		return this.sqlsessiontemplate.selectList("view.rutinRE", mbti);
		
	}
	
	  
}
