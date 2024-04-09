package com.example.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.User.Service.UserCartService;
import com.example.test.User.Service.UserService;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;


import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class MbtiTripApplicationTests {

	UserCartService userCartService;
	
	@Autowired
	UserService userService;
	
	UserCartDTO userCartDTO =new UserCartDTO() ;

	
	ReplaceDTO replaceDTO = new ReplaceDTO();
	
	@Autowired
	UserCartDAO userCartDAO;
	
	@Autowired
	UserHistoryDAO userHistoryDAO;
	
	UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
	
	@Autowired
	UserDAO userDAO;
	
	@Test
	void contextLoads() {
		userHistoryDTO.setUserName("testUser4");
		
		String userName = "testUser4";
		Integer userUID = userService.findByUID(userName);
		Map<String, Object> user = userService.getInfo(userUID);
		
		/*@param 4.9 신성진 Bis 유저 관련한 기능 테스트*/
		int cnt = 1 ;
		List<HashMap<String, Object>> userItems =userDAO.getMyItem(userName);
		for(HashMap<String,Object> userItem : userItems) {
			log.info("userItem ==> {}", userItem);
		}
		
		/*4/9 신성진 userCart TEST 진행 */
		/*insert TEst 완료 */
//		DateTimeFormatter dateformatter =  DateTimeFormatter.ofPattern("yyyy-mm-dd");	
//		LocalDate lday = LocalDate.now();
//		LocalDate end =lday.plusDays(1);
//		userCartDTO.setUserName(userName);
//		userCartDTO.setStartDate(lday);
//		userCartDTO.setEndDate(end);
//		userCartDTO.setItemId(1);
//		int result = userCartDAO.insertItem(userCartDTO);
//		log.info("cartInsertTEst ==> {}", (result==1) ? true : false);
		
//		userCartDTO.setUserName(userName);
//	
//
//		
//		int result = userCartDAO.updatePaymentsSuccess(userCartDTO.getUserName());
//		log.info("updateCk ==> {}", (result ==0)? "예약 실패" : "예약 됨 ");
//		List<HashMap<String,Object>> userpays = userCartDAO.detail_pay(userCartDTO);
//		if(userpays.isEmpty()) {
//			log.info("예약된 항목 없음");
//		}
//		else {
//			for(HashMap<String, Object> userpay :userpays) {
//				log.info(userpay);
//			}
//		}
//		
//		int falseResult = userCartDAO.updatePaymentFalse(userName);
//		
//		List<HashMap<String,Object>> userCarts = userCartDAO.detail(userCartDTO);
//		log.info("updateCk ==> {}", (falseResult ==0)? "예약취소 실패" : "예약취소ㅠ 됨 ");
//		for(HashMap<String,Object> userCart : userCarts) {
//		log.info("message userCart -=> {}", userCart);
//	}
		/*최근 조회 게시물 viewReturn~ 테스트 끝 */
//		String userName = "testUser4";
//		List<PostDTO> userViewPO = 	userHistoryDAO.viewReturnPO(userName);
//		for(PostDTO userView : userViewPO ) {
//			log.info("userViewPO =>  {}"+userView);
//		}
		
		

		
		
		
		/*4/8 userHistory Test 작업자 신성진*/
		/*viewCK 사용자가 조회한 아이템, 게시글 확인 가능 */
//		UserHistoryDTO userHIs = new UserHistoryDTO();
//		userHIs.setPostid(3);
//		userHIs.setUserName(userName);
//		
//		userHistoryDAO.viewCkPO(userHIs);
//		
		
		/*
		List<HashMap<String, Object>> userpost = userHistoryDAO.userCreatePost("testUser4");
		for(HashMap<String, Object> up: userpost) {
			log.info("usercreatePost  ===>{}", up);
		}
		
		List<HashMap<String, Object>> userQnA = userHistoryDAO.userCreateQnA("testUser4");
		for(HashMap<String, Object> up: userQnA) {
			log.info("userQnAㅋㅅ : ===>{}", up);
		}

		
		log.info("UXMbti ===>{}", userHistoryDAO.uxMbti(userName));
		
		List<HashMap<String, Object>> UXs1=userHistoryDAO.rutinADByUx("ENFP");
		List<HashMap<String, Object>> UXs2=userHistoryDAO.rutinREByUx("ISTJ");
			for(HashMap<String, Object> up: UXs1) {
			log.info("rutinADByUx : ===>{}", up);
		}
		for(HashMap<String, Object> up: UXs2) {
			log.info("rutinREByUx : ===>{}", up);
		}
		*/
//		
		
		
//		List<PostDTO> userViewPost = userHistoryDAO.viewReturnPO(userName);
//		for(PostDTO userView : userViewPost) {
//			log.info("userVeiw Post ===> {}", userView);
//		}
		
//		List<ItemDTO> userViewReplace = userHistoryDAO.viewReturnRE(userName);
//		for(ItemDTO userView : userViewReplace) {
//			log.info("userVeiw RE ===> {}", userViewReplace);
//		}
//		List<ItemDTO> userViewAdventure = userHistoryDAO.viewReturnAD(userName);
//		for(ItemDTO userView : userViewAdventure) {
//			log.info("userVeiw AD ===> {}", userViewAdventure);
//		}
		
//		String mbti = "ISTJ";
//		
//		List<HashMap<String, Object>> userLikeMBti = userHistoryDAO.uxMbti(userName);
//		log.info(userLikeMBti);
//
//		for(HashMap<String, Object> up: userLikeMBti) {
//			log.info("rutinByUx : ===>{}", up);
//		}
//		
		
//		List<HashMap<String, Object>> UXs3=userHistoryDAO.rutinByUx("ENFP");
//	
//		for(HashMap<String, Object> up: UXs3) {
//			log.info("rutinByUx : ===>{}", up);
//		}
		
		
		
		
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
