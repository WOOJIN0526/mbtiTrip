package com.example.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.DTO.UserHistoryDTO;
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
	
	@Autowired
	UserHistoryDAO userHistoryDAO;
	
	UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
	
	@Test
	void contextLoads() {
		userHistoryDTO.setUserName("testUser4");
		
		List<HashMap<String, Object>> userpost = userHistoryDAO.userCreatePost("testUser4");
		for(HashMap<String, Object> up: userpost) {
			log.info("usercreatePost : ===>{}", up);
		}
		
		
		
		
		
		
//		List<HashMap<String, Object>> ULM = userHistoryDAO.uxMbti(userHistoryDTO.getUserName());
//		
//		log.info("how key ===>{}", ULM.get(0).get("mbti"));

		
		/*이하 테스트 완료, 작업자 신성진*/
//		String mbti = "ISTJ";
//		//중복 값 때문에 2건 뜨는거 맞음 
//		List<HashMap<String, Object>> rutinRE = userHistoryDAO.rutinREByUx(mbti);
//		int d = 1;
//		for(HashMap<String,Object> Day : rutinRE) {
//		if(d==5) {
//			d = 1;
//		}
//		log.info(String.format("Day%s RE ==> {}", d), Day);
//		d++;
//		}
//		
//		String mbti = "ISTJ";
//		//중복 값 때문에 2건 뜨는거 맞음 
//		List<HashMap<String, Object>> rutinAD = userHistoryDAO.rutinADByUx(mbti);
//		int d = 1;
//		for(HashMap<String,Object> Day : rutinAD) {
//		if(d==5) {
//			d = 1;
//		}
//		log.info(String.format("Day%s ADV ==> {}", d), Day);
//		d++;
//		}
		
		
		
//		Test완
//		String largestMbti = (String) ULM.get(0).get("mbti");
//		log.info("ParsingString ==>{}", largestMbti);
//		String mbti = "ISTJ";
//		List<HashMap<String, Object>> rutin = userHistoryDAO.rutinByUx(mbti);
//		log.info("rutin info ====> {}", rutin);
//		int d = 1;
//		for(HashMap<String,Object> Day : rutin) {
//			
//			if(d==5) {
//				d = 1;
//			}
//			log.info(String.format("Day%s ==> {}", d), Day);
//			d++;
//			
//		}
		/*UX 기반 사용자 추천 시스템 */
		
		
//		userHistoryDTO.setReplaceid(2);
//		userHistoryDAO.viewCkRE(userHistoryDTO);
//		log.info("userName ==>{}", userHistoryDTO.getUserName());
//		List<ReplaceDTO> viewRE = userHistoryDAO.viewReturnRE(userHistoryDTO.getUserName());
//		List<AdventureDTO> viewAD = userHistoryDAO.viewReturnAD(userHistoryDTO.getUserName());
//		List<PostDTO> viewPO = userHistoryDAO.viewReturnPO(userHistoryDTO.getUserName());
//		
//		log.info("replace View ===> {} ", viewRE);
//		for(ReplaceDTO view : viewRE) {
//			log.info("view return ===>{}", view.getReplaceLocation());
//		}
//		for(AdventureDTO view : viewAD) {
//			log.info("view return ===>{}", view.getAdventureName());
//		}
//		for(PostDTO view : viewPO) {
//			log.info("view return ===>{}", view.getContent());
//		}
	
		
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
