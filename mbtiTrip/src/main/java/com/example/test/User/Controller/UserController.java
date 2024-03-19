package com.example.test.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userSerivice; 
	
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public ModelAndView singup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("sign_up");
		return mav;
	}
	
	@RequestMapping(value = "/signup", method=RequestMethod.POST)
	public ModelAndView singup(@ModelAttribute UserDTO userdto) {		
		ModelAndView mav = new ModelAndView();
		mav.addObject(userdto);
		mav.setViewName("main");
		return mav;
	}
}
