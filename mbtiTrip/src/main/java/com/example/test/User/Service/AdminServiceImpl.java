package com.example.test.User.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.test.User.DAO.adminDAO;
import com.example.test.User.DTO.AdminDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AdminServiceImpl implements adminService{

	@Autowired(required=true)
	private AdminDTO adminDto;
	@Autowired
	private adminDAO adminDao;
	
	
	@Override
	public boolean userBaned(String username) {
		boolean baned = false;
		int reportCnt = adminDao.userReportCnt(username);
		
		if(reportCnt >10) {
		  int ck = adminDao.userBaned(username);
			  if(ck == 1) {
				  baned = true;
			  }
		}
		return baned;
	}

  ;
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
	
	
	@Scheduled(cron = "0 0 0 * * *") 
	@Override
	public void dailyrating() {
		List<String> dailyUser = adminDto.getDailyRating();
		this.adminDto.setRating(0);
		dailyUser.clear();
		this.adminDto.setDailyRating(dailyUser);
	}


	@Override
	public Map<String, Integer>  userCnt() {
		Map<String,Integer> cnt = adminDao.userCount();
		return cnt;
	}


	@Override
	public int postCnt() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int replaceCnt() {
		// TODO Auto-generated method stub
		return 0;
	}

	



	@Override
	public int adventureCnt() {
		// TODO Auto-generated method stub
		return 0;
	}


	


}
