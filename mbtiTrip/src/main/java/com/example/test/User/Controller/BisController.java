package com.example.test.User.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/bis")
public class BisController {

	
	@Autowired
	private UserService userService;
	
//	@RequestMapping(value="/{UID}", method = RequestMethod.GET)
//	public ModelAndView mainUser(@PathVariable Integer UID , UserDTO userDTO, ModelAndView mav) {
//		// 사용자가 등록해둔 replace, adventure 정보
		// replace를 조회한 사람, 장바구니에 넣어둔 사람, 이벤트 정보, 누적 인기 순위? 
//		// 
//		mav.addObject("user", user);s
//		
//		return mav;
//		@RequestMapping(value = "/signup/bis", method=RequestMethod.GET)
	
	
	public ModelAndView signUpBis(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String currentUrl = request.getRequestURI().toString();
		mav.addObject("currentUrl", currentUrl);
		mav.setViewName("sign_up");
		return mav;
	}
	
	
	@RequestMapping(value = "/signup/bis", method=RequestMethod.POST)
	@ResponseBody
	public boolean singupUserBis(@RequestBody UserDTO userdto) {		
		//ModelAndView mav = new ModelAndView();     // 아직 비번 암 복호화 안됌 ㅋㅌ
		userdto.setUserrole(User_Role.bis);		
		int result = userService.createBis(userdto);
		boolean chk = false;
		if(result == 1) {
			chk = true;
			//mav.addObject(result);
		} 
		return chk;
	}
	
	
	@RequestMapping(value = "/bis/mypage/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageBis(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	
	@RequestMapping(value = "/bis/mypage/update/{UID}", method = RequestMethod.POST)
	public ModelAndView Bisupdate(@ModelAttribute UserDTO userdto, ModelAndView mav) {
		
		try {
			int result= userService.BisUpdate(userdto);
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
