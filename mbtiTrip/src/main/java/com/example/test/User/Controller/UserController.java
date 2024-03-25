package com.example.test.User.Controller;

import java.io.Console;
import java.util.Map;

import org.apache.catalina.security.SecurityConfig;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
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

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@Import({SecurityConfig.class})
public class UserController {

	@Autowired
	UserService userService; 
	
//	@RequestMapping(value = "/signup/user", method=RequestMethod.GET)
//	public String signUpUser() {
//		return "sign_up";
//	}
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signUpSelect() {
		return "sign_up_select";
	}
	
	@RequestMapping(value = "/signup/user", method = RequestMethod.GET)
	public ModelAndView signUpUser(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView();
	    String currentUrl = request.getRequestURI().toString();
	    mav.addObject("currentUrl", currentUrl);
	    mav.setViewName("sign_up");
	    return mav;
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

	@RequestMapping(value = "/login_A", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_form");
		return mav;
	}
	
	@RequestMapping(value = "/login_A", method=RequestMethod.POST)
	public String login(@ModelAttribute UserDTO userdto, Model model) {
		System.out.print(userdto.toString());
		 Map<String, Object> user = userService.login(userdto);
		 System.out.print(userdto.toString());
		 model.addAttribute("user", user);
		 try {
			 if(user.get("UID")  != null) { 
				 model.addAttribute(user);
				 if(user.get("userrole").equals(User_Role.user.toString())) {
					 return String.format("/main/%s/%s", user.get("userrole") ,user.get("UID"));
//					 return String.format("redirect:/user/%s", user.get("UID"));
				 	}
				 
				 else if(user.get("userrole").equals(User_Role.bis.toString())) {
					 return String.format("/main/%s/%s",user.get("userrole"), user.get("UID"));
//					 return String.format("redirect:/bis/%s", user.get("UID"));
				 	}	 
				 else if(user.get("userrole").equals(User_Role.admin.toString())) {
					 return "main";
//					 return String.format("redirect:/admin/%s", user.get("UID"));
				 }
			   }
			 else {
				 model.addAttribute("message", "사용자 정보를 찾을 수 없습니다.");
				 return "redirect:/login_A";
			}
		} catch (Exception e) {
			model.addAttribute("message", e);
			System.out.println("exception");

			return "redirect:/login_A";
		}
		return "redirect:/";
	}
	
	
	@RequestMapping(value ="/" , method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		return mv;
	}
	
	
	@RequestMapping(value = "/main/{userrole}/{UID}", method = RequestMethod.GET)
	public ModelAndView main(@PathVariable String userrole, 
							@PathVariable String UID,
							@RequestBody UserDTO userdto,
							ModelAndView mav) {
		mav.addObject(userdto);
		return mav;
		
		
	}
	
//	@RequestMapping(value="/user/{UID}", method = RequestMethod.GET)
//	public ModelAndView mainUser(@PathVariable Integer UID , UserDTO userDTO, ModelAndView mav) {
//		// userMBTI에 따른 replace, adventure 정보 
//		// 인기 POSt 정보, 
//		// 
//		mav.addObject("user", user);
//		
//		return mav;
//	}
	
	@RequestMapping(value = "/mypage/user/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageUser(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}

	@RequestMapping(value = "/user/mypage/update/{UID}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
//		String Uid = userdto.getUID();
//		mav.addObject(userdto);
// 0321 최우진 유저데이터가 들어가야 될거같아서 주석하고 밑으로 바꿔봄		
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("user_update");
		return mav;
	}
	
	@RequestMapping(value = "/user/mypage/updatse/{UID}", method = RequestMethod.POST)
	public ModelAndView update(@ModelAttribute UserDTO userdto, ModelAndView mav) {
		
		try {
			int result= userService.userUpdate(userdto);
			if(result == 1) {
				mav.addObject(userdto);
				mav.addObject("message", "회원정보가 수정 되었습니다");
				mav.setViewName(String.format("redirect:/mypage/%s", userdto.getUID()));
			}
			else {
				throw new Exception("정보가 정상적으로 수정되지 않았습니다");
			}
		} catch (Exception e) {
			mav.addObject("message", e.getClass());
			mav.setViewName("mypage");
		}
		return mav;
	}
	

	
}
