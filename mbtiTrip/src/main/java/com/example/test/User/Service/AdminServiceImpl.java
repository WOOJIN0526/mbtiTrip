package com.example.test.User.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.adminDAO;
import com.example.test.User.DTO.AdminDTO;
import com.example.test.session.SessionUserCnt;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminServiceImpl implements AdminService{

	@Autowired(required=true)
	private AdminDTO adminDto;
	@Autowired
	private adminDAO adminDao;
	
	
	@Override
	public boolean userBaned(String username) {
		boolean baned = false;
		int ck = adminDao.userBaned(username);
		if(ck != 0) {
			baned = true;
		}
		
		return baned;
	}

	
	@Override
	public boolean userUnblock(String username) {
		boolean unblock = false;
		int ck = adminDao.userBaned(username);
		if(ck != 0) {
			//ck가 0이 아닌 경우 block 성공
			unblock = true;
		}
		return unblock;
	}

	/*오늘 접속한 사용자수 */
	@Override
	public int rating(String userName) {
		int rating = adminDto.getRating();
		List<String> dailyUser = adminDto.getDailyRating();
		if(dailyUser.contains(userName)) {
			return rating;
		}
		else {
			this.adminDto.setRating(adminDto.getRating()+1);
			 rating = adminDto.getRating();
		}
		return rating;
	}
	
	
	//매일 자정마다 새로운 테이블 생성 
	@Scheduled(cron = "0 0 0 * * *") 
	@Override
	public void dailyrating() {
		List<String> dailyUser = adminDto.getDailyRating();
		this.adminDto.setRating(0);
		dailyUser.clear();
		this.adminDto.setDailyRating(dailyUser);
	}


	/*총 user수 */
	@Override
	public Map<String, Integer>  userCnt() {
		Map<String,Integer> cnt = adminDao.userCount();
		return cnt;
	}
	
	/*userType 분포도*/
	@Override
	public List<HashMap<String, Object>> mbtiCnt() {
		List<HashMap<String, Object>> mbtiCnt = adminDao.mbtiCnt();
		return mbtiCnt;
	}

	/*이하 유저 관리 */
	@Override
	public List<HashMap<String, Object>> userList() {
		List<HashMap<String, Object>> userList = adminDao.userList();
		return userList;
	}

	@Override
	public List<HashMap<String, Object>> bisList() {
		List<HashMap<String, Object>> bisList = adminDao.bisList();
		return bisList;
	}
	
	
	@Override
	public int liveUserCnt() {
		int liveUser = new SessionUserCnt().getCnt(); 
		return liveUser;
	}




	




	


}
