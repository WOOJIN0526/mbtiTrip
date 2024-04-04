package com.example.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.Service.UserCartService;
import com.example.test.replace.DTO.ReplaceDTO;import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class MbtiTripApplicationTests {

	UserCartService userCartService;
	
	
	UserCartDTO userCartDTO =new UserCartDTO() ;

	
	ReplaceDTO replaceDTO = new ReplaceDTO();
	
	@Autowired
	UserCartDAO userCartDAO;
	
	@Test
	void contextLoads() {
	//userCart Set
//	userCartDTO.setUserName("testUser4");
//	replaceDTO.setReplaceID(1);
//	userCartDTO.setReplaceInfo(replaceDTO);
////	userCartDTO.setStartDate(LocalDateTime.now());
////	userCartDTO.setEndDate(LocalDateTime.now());
//	int result =userCartDAO.deleteReplace(userCartDTO);
//	log.info("result  ->{}", result);
	
	//	List<UserCartDTO> cartTest = userCartDAO.detail(userCartDTO);
//	Integer finalPrice = 0;
//	for (UserCartDTO user : cartTest) {
//		log.info("detail test => {} ", user.getReplaceInfo().getReplaceAdmin());
//		log.info("adventure Test => {}", user.getAdventureInfo());
//		finalPrice += user.getReplaceInfo().getReplacePrice();
//		if(user.getAdventureInfo().getAdventurePrice()!=null) {
//			finalPrice += user.getAdventureInfo().getAdventurePrice();
//		}
//		
//	}
//	log.info("Final Price ==>{}", finalPrice);
	}



	
	
	
	
}
