package com.example.test.User.Controller;

import java.io.Console;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userSerivice; 
	
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signUp() {
		return "sign_up";
	}
	
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView singup(@ModelAttribute UserDTO userdto) {		
		ModelAndView mav = new ModelAndView();
		System.out.print(userdto);
		mav.setViewName("login_form");
		return mav;
	}
}
