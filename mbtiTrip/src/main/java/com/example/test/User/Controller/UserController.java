package com.example.test.User.Controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService; 
	
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signUp() {
		return "sign_up";
	}
	
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	@ResponseBody
	public boolean singup(@RequestBody UserDTO userdto) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ
		boolean result = false;
		System.out.println(userdto.getPhone());
		
		System.out.println(userdto.toString());
		if(userService.createUser(userdto) == 1) {
			result = true;
			//mav.addObject(result);
		} 
		else {
			//mav.addObject(result);

		}	
		return result;
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
			return "main";
		}

		
		return "/login";
	}
}
