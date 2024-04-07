package com.example.test.User.Controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.session.SessionUserCnt;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class AdminController {

	@Autowired 
	private UserService userService;

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
		mav.setViewName(String.format("redirect:/user/mypage/%s", userUID));
		return mav;
	}
	
	@RequestMapping(value = "/admin/mypage", method = RequestMethod.GET)
	public ModelAndView mypageadmin(Principal principal, UserDTO userdto, ModelAndView mav){
		Integer userUID = userService.findByUID(principal.getName());
		Map<String, Object> map = userService.getInfo(userUID);
		Integer mbti = (Integer) map.get("mbti");
		
		//사이트 관리 페이지에 필요한 정보들 들어갈 예정 
		// EX, 유저수, 게시물 수, 등록된 장소 수 등  
		
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}	
	
	@RequestMapping("/userCount")
	public ModelAndView userCount(ModelAndView mav, SessionUserCnt session) {
		log.info("session count =>{}", session.getCnt());
		int userCount = session.getCnt();
		mav.addObject("userCount", userCount);
		mav.setViewName("userCount");
		return mav;
	}
}
