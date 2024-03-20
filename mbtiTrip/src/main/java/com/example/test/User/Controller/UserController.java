package com.example.test.User.Controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.DTO.User_Role;
import com.example.test.User.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService; 
	
	@RequestMapping(value = "/signup/user", method=RequestMethod.GET)
	public String signUp() {
		return "sign_up";
	}
	
	@RequestMapping(value = "/signup/user", method=RequestMethod.POST)
	@ResponseBody
	public boolean singup(@RequestBody UserDTO userdto) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ
		userdto.setUserrole(User_Role.admin);
		userdto.toString();
		int result = userService.createUser(userdto);
		boolean chk = false;
	
		if(result == 1) {
			chk = true;
			//mav.addObject(result);
		} 
		return chk;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_form");
		return mav;
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String login(@ModelAttribute UserDTO userdto, Model model) {
		String result = userService.login(userdto);
		if(result!=null) {
			return "/mypage";
		}
		return "/login";
	}
	
	//username, mbti, password, Post, cart, 
	
	@RequestMapping(value = "/mypage/{UID}", method = RequestMethod.GET)
	public ModelAndView mypage(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		String Uid = userdto.getUID();
		mav.addObject(userdto);
		mav.setViewName("mypage");
		return mav;
	}
	
//	@RequestMapping(value = "/mypage/{UID}", method = RequestMethod.POST)
//	public ModelAndView mypage(@ModelAttribute UserDTO userdto, ModelAndView mav) {
//		
//		return mav;
//	}
	
	@RequestMapping(value = "/mypage/update/{UID}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		String Uid = userdto.getUID();
		mav.addObject(userdto);
		mav.setViewName("user_update.htm");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/update/{UID}", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute UserDTO userdto, ModelAndView mav) {
		
		try {
			int result= userService.userUpdate(userdto);
			if(result == 1) {
				mav.addObject(userdto);
				mav.addObject("message", "회원정보가 수정 되었습니다");
				mav.setViewName(String.format("redirect:/mypage/%s", userdto.getUID()));
			}
			else {
				throw new Exception();
			}
		} catch (Exception e) {
			mav.addObject("message", e.getClass());
			mav.setViewName("mypage");
		}
		return mav;
	}
	
}
