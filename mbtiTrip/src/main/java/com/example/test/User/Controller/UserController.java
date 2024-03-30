package com.example.test.User.Controller;

import java.io.Console;
import java.security.Principal;
import java.util.Map;

import org.apache.catalina.security.SecurityConfig;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.DTO.User_Role;
import com.example.test.User.Service.LoginService;
import com.example.test.User.Service.QnAService;
import com.example.test.User.Service.UserService;
import com.example.test.User.Service.UserServiceImpl;

import groovy.transform.ToString;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@Import({SecurityConfig.class})
public class UserController {

	@Autowired
	private UserServiceImpl userService; 
	
	@Autowired
	private QnAService qnaService;
	
	@Autowired
	private LoginService loginservice;
	

	private BCryptPasswordEncoder bcrypasswordEncoder = new BCryptPasswordEncoder(); 
	
//	@RequestMapping(value="/access_denied_page", method=RequestMethod.GET)
//	public String DeniedPage  {       
//		return "access_denied_page";  
//	}
	
	
//	@RequestMapping(value = "/signup/user", method=RequestMethod.GET)
//	public String signUpUser() {
//		return "sign_up";
//	}
	@RequestMapping(value = "/signup", method=RequestMethod.GET)
	public String signUpSelect() {
		return "sign_up_select";
	}
	
	@RequestMapping(value = "/user/signup", method = RequestMethod.GET)
	public ModelAndView signUpUser(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView();
	    log.info("signup user,get도착 ");
	    String currentUrl = request.getRequestURI().toString();
	    mav.addObject("currentUrl", currentUrl);
	    mav.setViewName("sign_up");
	    return mav;
	}
	
	@RequestMapping(value = "/user/signup", method=RequestMethod.POST)
	public ModelAndView singupUser(@RequestBody UserDTO userdto , ModelAndView mav) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ

		log.info("signup user,Post도착 ");
		log.info("UserDetail :  {}", userdto);
		userdto.setUserrole(User_Role.user.getValue());  //보류 
		String userPassword = userdto.getPassword();
		log.info("userPassword : {}", userPassword);
		String encodePassword = bcrypasswordEncoder.encode(userPassword);
		log.info("encodePassword : {}", encodePassword);
		userdto.setPassword(encodePassword);
		int result = userService.createUser(userdto);
		boolean chk = false;
		
		if(result == 1) {
			chk = true;
			mav.addObject(chk);
			mav.setViewName("redirect:/login_A");
		} 
		mav.addObject(chk);
		return mav;
	}  

	@RequestMapping(value = "/login_A", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login_form");
		return mav;
	}
	
	@RequestMapping(value = "user/login/success")
	public ModelAndView UserSuccess(ModelAndView mav, 
								Principal princ) {
		String userName = princ.getName();
		Integer userUID = userService.findByUID(userName);
		Map<String, Object> user = userService.getInfo(userUID);
		log.info("UserLoginSuccess = UserINFo= {}", user);
		
		mav.addObject("user", user);
		mav.setViewName(String.format("redirect:/user/main/%s", userUID));
		return mav;
	}
	
//	@RequestMapping(value = "/login_A", method=RequestMethod.POST)
//	public String login(@ModelAttribute UserDTO userdto, Model model) {
//		System.out.print(userdto.toString());
//		 Map<String, Object> user = userService.login(userdto);
//		 System.out.print("user정보 저장 map = "+user.toString());
//		 model.addAttribute("user", user);
//		 try {
//			 if(user.get("UID")  != null) { 
//				 
//				 model.addAttribute(user);
//				 System.out.println(User_Role.user.toString());
//				 if(user.get("userrole").equals(User_Role.user.getValue())) {
//					 System.out.println("유저 로그인 정보 조회 성공");
//					
//					 return String.format("redirect:user/main/%s", user.get("UID"));
////					 return String.format("redirect:/main/%s/%s", user.get("userrole") ,user.get("UID"));
////					 return String.format("redirect:/user/%s", user.get("UID"));
//				 	}
//				 else if(user.get("userrole").equals(User_Role.bis.getValue())) {
//					 return String.format("redirect:bis/main/%s", user.get("UID"));
////					 return String.format("redirect:/bis/%s", user.get("UID"));
//				 	}	 
//				 else if(user.get("userrole").equals(User_Role.admin.getValue())) {
//					 return "redirect:/main";
////					 return String.format("redirect:/admin/%s", user.get("UID"));
//				 }
//			   }
//			 else {
//				 model.addAttribute("message", "사용자 정보를 찾을 수 없습니다.");
//				 return "redirect:/login_A";
//			}
//		} catch (Exception e) {
//			model.addAttribute("message", e);
//			e.printStackTrace();
//
//			return "redirect:/login_A";
//		}
//		return "redirect:/login_A";
//	}
	
	
	@RequestMapping(value ="/" , method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mv) {
		mv.setViewName("main");
		return mv;
	}
	
	
	@RequestMapping(value = "/user/main/{UID}", method = RequestMethod.GET)
	public ModelAndView main(
							@PathVariable("UID") Integer UID,
							ModelAndView mav) {
		log.info("main 접속 중 ");
		Map<String, Object> user = userService.getInfo(UID);
		log.info("userMain info = {} ", user);
		mav.addObject("user", user);
		mav.setViewName("main");
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
	
		
	@RequestMapping(value = "/user/mypage/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageUser(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
	
		Map<String, Object> map = userService.getInfo(UID);
		System.out.println("mypageLoad="+map.toString());
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
