package com.example.test.User.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

@Controller
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
	
	@RequestMapping(value = "/admin/mypage/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageadmin(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		Integer mbti = (Integer) map.get("mbti");
		
		//사이트 관리 페이지에 필요한 정보들 들어갈 예정 
		// EX, 유저수, 게시물 수, 등록된 장소 수 등  
		
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}	
	
		
}
