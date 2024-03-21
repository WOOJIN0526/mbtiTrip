package com.example.test.User.Controller;

import java.io.Console;
import java.util.Map;

import org.hibernate.validator.internal.util.logging.Log;
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
	public String signUpUser() {
		return "sign_up";
	}
	
	@RequestMapping(value = "/signup/user", method=RequestMethod.POST)
	@ResponseBody
	public boolean singupUser(@RequestBody UserDTO userdto) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ
		userdto.setUserrole(User_Role.user);
		userdto.toString();
		int result = userService.createUser(userdto);
		boolean chk = false;
	
		if(result == 1) {
			chk = true;
			//mav.addObject(result);
		} 
		return chk;
	}
	
	@RequestMapping(value = "/signup/bis", method=RequestMethod.GET)
	public String signUpBis() {
		return "sign_up";
	}
	
	@RequestMapping(value = "/signup/bis", method=RequestMethod.POST)
	@ResponseBody
	public boolean singupUserBis(@RequestBody UserDTO userdto) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ
		userdto.setUserrole(User_Role.bis);
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
	public String login(@ModelAttribute UserDTO userdto, Model model) {;
		 Map<String, Object> user = userService.login(userdto);
		 try {
			 if(user.get("UID")  != null) {
				 
				 model.addAttribute(user);
				 if(user.get("userrole").equals(User_Role.user.toString())) {
					 return String.format("redirect:/mypage/user/%s", user.get("UID"));
				 	}
				 else if(user.get("userrole").equals(User_Role.bis.toString())) {
					 return String.format("redirect:/mypage/bis/%s", user.get("UID"));
				 	}	 
				 else if(user.get("userrole").equals(User_Role.admin.toString())) {
					 return String.format("redirect:/mypage/admin/%s", user.get("UID"));
				 }
			   }
			 
			 else {
				 model.addAttribute("message", "사용자 정보를 찾을 수 없습니다.");
				 return "redirect:/login";
			}
		} catch (Exception e) {
			model.addAttribute("message", e);
			System.out.println("exception");

			return "redirect:/login";
		}
		
		return "";
	
	}
	
	//username, mbti, password, Post, cart, 
	
	@RequestMapping(value = "/mypage/user/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageUser(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	
	
	
	@RequestMapping(value = "/mypage/bis/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageBis(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		
		//Myreplace 로직 들어갈 예정, 나의 숙소 정보 -> UID랑 replace의 admin 이랑 조인 쳐서 
		
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/admin/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageadmin(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		Integer mbti = (Integer) map.get("mbti");
		
		//사이트 관리 페이지에 필요한 정보들 들어갈 예정 
		// EX, 유저수, 게시물 수, 등록된 장소 수 등  
		
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	

	
	@RequestMapping(value = "/mypage/User/update/{UID}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
//		String Uid = userdto.getUID();
//		mav.addObject(userdto);
// 0321 최우진 유저데이터가 들어가야 될거같아서 주석하고 밑으로 바꿔봄		
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("user_update");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/User/update/{UID}", method = RequestMethod.POST)
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
	
	@RequestMapping(value ="/main" , method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		return mv;
	}
	
}
