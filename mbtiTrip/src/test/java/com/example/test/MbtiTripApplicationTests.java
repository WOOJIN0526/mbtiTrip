package com.example.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
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
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.PostServiceImpl;
import com.example.test.User.DAO.QnADAO;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.DAO.adminDAO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.DTO.UserHistoryDTO;
import com.example.test.User.DTO.User_Role;
import com.example.test.User.Service.QnAServiceImpl;
import com.example.test.User.Service.UserCartService;
import com.example.test.User.Service.UserService;
import com.example.test.item.ItemType;
import com.example.test.item.DAO.ItemDAO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.testExcepion.Cart.CartException;
import com.example.testExcepion.Cart.CartExceptionEnum;
import com.example.testExcepion.Item.ItemException;
import com.example.testExcepion.Post.PostException;
import com.example.testExcepion.Post.PostExceptionEnum;
import com.example.testExcepion.SignUP.SignUpException;
import com.example.testExcepion.SignUP.SignUpExceptionEunm;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.google.common.collect.Multiset.Entry;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class MbtiTripApplicationTests {

	UserCartService userCartService;
	
	@Autowired
	UserService userService;
	
	UserCartDTO userCartDTO =new UserCartDTO() ;

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	ReplaceDTO replaceDTO = new ReplaceDTO();
	
	@Autowired
	UserCartDAO userCartDAO;
	
	@Autowired
	UserHistoryDAO userHistoryDAO;
	
	UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
	
	@Autowired
	UserDAO userDAO;

	@Autowired
	adminDAO adminDao;
	@Autowired
	QnAServiceImpl qnaService;
	
	@Autowired
	PostDAO postDao;
	
	@Autowired
	QnADAO qnaDao;
	
	@Autowired
	ItemDAO itemDAO;
	
	@Test
	void contextLoads() {
		String location = "광주";
		location += '%';
		log.info("location => {}",location);
		List<ItemDTO> result = itemDAO.searchLocation(location);
		for(ItemDTO re : result) {
			log.info("location search ==>{}", re);
		}
		
		
		
		
		
//		UserDTO user = new UserDTO();
//		user.setUserId("testUser#66226");
//		user.setUserName("faust");
//		user.setPassword("testse1ts");
//	
//		userCartDTO.setItemId(1);
//		userCartDTO.setStartDate(LocalDate.now().plusDays(2));
//		userCartDTO.setEndDate(LocalDate.now().plusDays(1));
//
//
//		
//		Authentication auths = SecurityContextHolder.getContext().getAuthentication();
//		log.info("{}", auths);
//		String userAuth = null;
//		userAuth = User_Role.bis.getValue();
//		log.info("split 전 ==> {} ", userAuth);
//		
//		switch(userAuth) {
//		case "ROLE_USER" : log.info("user확인");break;
//		case "ROLE_BIS" : log.info("bis확인"); break;
//		}
//		
		
//		if(userCartDTO.getItemId() == null) {
//			throw new CartException(CartExceptionEnum.CART_NOTFOUND_ITEM);
//		}
//		
//		if(userCartDTO.getStartDate() == null) {
//			throw new CartException(CartExceptionEnum.CART_STARTDATE_NULL);
//			}
//		
//		if(userCartDTO.getEndDate() == null) {
//			throw new CartException(CartExceptionEnum.CART_ENDDATE_NULL);
//			}
//		
//		if(userCartDTO.getStartDate().isBefore(LocalDate.now())){
//			throw new CartException(CartExceptionEnum.CART_STARTDATE_MISMATCH);
//		}
//		
//	    if(userCartDTO.getEndDate().isBefore(userCartDTO.getStartDate())) {
//			throw new CartException(CartExceptionEnum.CART_ENDDATE_MISMATCH2);
//		}
		
//		user.setUserName("testUser4");
//		ItemDTO item = new ItemDTO();
//		item.setItemName("test");
//		item.setContents("tts");
//		item.setType(ItemType.adventure);
//		item.setUsername(user);
//		item.setPrice(1);
//		item.setLocation("미켈란젤로");
//		
//		ItemException.validationItem(item);
//	
//		ItemDTO post = new ItemDTO();
//		ItemException.validationItem(post);
//		int ck = postDao.titleCk("testTitleOne");
//		log.info("{}", ck);
//		boolean userCk = false;
//		if(userDAO.getByUserId("testUser4").getUserId() != null) {
//			userCk = true;
//		
//		int idCk = this.sqlSessiontemplate.selectOne("user.ckUserID", "testUser4");
//		int nameCk = this.sqlSessiontemplate.selectOne("user.ckUsername", "강호동");
//		int mailCk = this.sqlSessiontemplate.selectOne("user.ckmail", "tesUser4");
//		log.info("validationCK ==={}",idCk);
//		log.info("validationCK ==={}",nameCk);
		boolean vaild = Pattern.matches("^[a-zA-Z0-9가-힣]*$", "testUser4");
		
		log.info("validationCK ==={}",!vaild);
//		List<HashMap<String, Object>> userList = adminDao.userList();
//		List<HashMap<String, Object>> bisList = adminDao.bisList();
//		for(HashMap<String, Object> list : userList) {
//			log.info("userList = > {}", list);
//			
//		}
//		for(HashMap<String, Object> list : bisList) {
//			log.info("bisList = > {}", list);
//			
//		}
		
//		List<HashMap<String, Object>> userMbtiCnt = adminDao.mbtiCnt();
//		for(HashMap<String, Object> mbtiCnt : userMbtiCnt) {
//			log.info(mbtiCnt);
//		}
//		Map<String, Integer> userCnt =adminDao.userCount();
//		Iterator<String> keys = userCnt.keySet().iterator();
//		while( keys.hasNext() ) {
//			String key = keys.next();
//			log.info("key =>{}", key);
//			log.info("userCnt === >{}", userCnt.get(key));
//		}
		
		
		
		
//		userHistoryDTO.setUserName("testUser4");
//		
//		String userName = "testUser4";
//		/*4.10 bis mypage 현재 예약 된 내역 확인 기능 Test*/
//		
//		List<ItemDTO> userViewItem =userHistoryDAO.viewReturnRE(userName);
//
//		
//		List<ItemDTO> userViewAD =userHistoryDAO.viewReturnAD(userName);
//
//		
//		List<PostDTO> userViewPo = userHistoryDAO.viewReturnPO(userName);
//
//		
//		List<List<?>> userViewInfo = new ArrayList<>();
//		userViewInfo.add(userViewItem);
//		userViewInfo.add(userViewAD);
//		userViewInfo.add(userViewPo);
//		log.info("userViewINFO ===> {}", userViewInfo);
//		
//		String mbti = "ISTJ";
//		List<HashMap<String, Object>> rutinByReplace =userHistoryDAO.rutinREByUx(mbti);
//		List<HashMap<String, Object>> rutinByAdventure = userHistoryDAO.rutinADByUx(mbti);
//		List<HashMap<String, Object>> rutinByUX = userHistoryDAO.rutinByUx(mbti);
//		log.info("message  RE=> {}", rutinByReplace);
//		log.info("message  AD=> {}", rutinByAdventure);
//		log.info("message  ALL=> {}", rutinByUX);
		
		/*@param 4.9 신성진 Bis 유저 관련한 기능 테스트*/
		/*itemList에 viewRatring값 삽입하기 성공*/
//		List<HashMap<String, Object>> userItems = userDAO.getMyItem(userName);
//		List<HashMap<String,Object>> viewRating = userHistoryDAO.getRatingItem(userName);
//		for(HashMap<String,Object> item : userItems) {
//			for(HashMap<String, Object> rate : viewRating) {
//				Set<String> keySet = rate.keySet();
//				for(String key : keySet) {
//					if(item.get("itemId").equals(rate.get("itemID"))) {
//						if(key.equals("viewRating")) {
//						 item.put(key, rate.get(key));
//						 
//						}
//					}
//				}
//				
//			}
//		}
//		for(HashMap<String,Object> z : userItems) {
//			log.info("Message ====>{}", z);
//		}
		
		
		
//		Integer userUID = userService.findByUID(userName);
//		Map<String, Object> user = userService.getInfo(userUID);
//		log.info("mypageBis => {}", user);
		
		
	
//		int cnt = 1 ;
//		List<HashMap<String, Object>> userItems =userDAO.getMyItem(userName);
//		for(HashMap<String,Object> userItem : userItems) {
//			log.info("userItem ==> {}", userItem);
//		}
		
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
