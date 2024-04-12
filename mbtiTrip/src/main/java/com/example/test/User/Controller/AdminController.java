package com.example.test.User.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.AdminService;
import com.example.test.User.Service.QnAService;
import com.example.test.User.Service.UserService;
import com.example.test.session.SessionUserCnt;
import com.google.rpc.context.AttributeContext.Response;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class AdminController {

	@Autowired 
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private QnAService qnaService;

//	@RequestMapping(value="/{UID}", method = RequestMethod.GET)
//	public ModelAndView mainUser(@PathVariable Integer UID , UserDTO userDTO, ModelAndView mav) {
//		// userMBTI에 따른 replace, adventure 정보 
//		// 인기 POSt 정보, 
//		
//		mav.addObject("user", user);
//		
//		return mav;
//	}
	
	@RequestMapping(value = "admin/login/success")
	public ModelAndView UserSuccess(ModelAndView mav, 
								Principal princ) {
		String userName = princ.getName();
		Integer userUID = userService.findByUID(userName);
		Map<String, Object> user = userService.getInfo(userUID);
		log.info("UserLoginSuccess = UserINFo= {}", user);
		
		mav.addObject("user", user);
		mav.setViewName(String.format("redirect:/"));
		return mav;
	}
	
	@RequestMapping(value = "/admin/mypage", method = RequestMethod.GET)
	public ModelAndView mypageadmin(Principal principal, UserDTO userdto, ModelAndView mav, SessionUserCnt session){
		Integer userUID = userService.findByUID(principal.getName());
		Map<String, Object> admin = userService.getInfo(userUID);
		
		//사이트 관리 페이지에 필요한 정보들 들어갈 예정 
		//전체 mbti 분포도 
		List<HashMap<String, Object>> userMbtiCnt = adminService.mbtiCnt(); 
		log.info("userMbtiCnt => {}", userMbtiCnt);
		// 사이트 이용자 수 
		Map<String, Integer> AllUserCnt = adminService.userCnt(); // user, bis, All 순 
		log.info("AllUserCnt => {}", AllUserCnt);
		//유저 리스트 
		List<HashMap<String, Object>> userList = adminService.userList();
		log.info("userList => {}", userList);
		//Bis 리스트
		List<HashMap<String, Object>> bisList = adminService.bisList();
		log.info("bisList => {}", bisList);
		//현재 활동 중인 사용자 수
		int activeUser = session.getCnt();
		// EX, 유저수, 게시물 수, 등록된 장소 수 등  
		mav.addObject("userMbtiCnt", userMbtiCnt);
		mav.addObject("AllUserCnt", AllUserCnt);
		mav.addObject("userList", userList);
		mav.addObject("bisList", bisList);
		mav.addObject("admin", admin);
		mav.setViewName("adminPage");
		return mav;
	}	
	
	
	@RequestMapping(value="admin/userBan", method=RequestMethod.POST)
	public ResponseEntity<String> userBan(@RequestParam("userName") String UserName){
		if(adminService.userBaned(UserName)) {
			return ResponseEntity.status(HttpStatus.OK).body("유저 차단이 완료 되었습니다");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유저가 차단 되지 않았습니다");
	}
	@RequestMapping(value="admin/unblock", method=RequestMethod.POST)
	public ResponseEntity<String> unblock(@RequestParam("userName") String UserName){
		if(adminService.userUnblock(UserName)) {
			return ResponseEntity.status(HttpStatus.OK).body("유저가 차단 해제 되었습니다");
			}
	
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("차단이 해제되지 않았습니다.");
	}
	
}
