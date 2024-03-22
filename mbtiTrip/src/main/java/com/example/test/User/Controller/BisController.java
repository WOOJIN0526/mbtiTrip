package com.example.test.User.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

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
//		mav.addObject("user", user);
//		
//		return mav;
//	}
	
	@RequestMapping(value = "/bis/mypage/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageBis(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		
		//Myreplace 로직 들어갈 예정, 나의 숙소 정보 -> UID랑 replace의 admin 이랑 조인 쳐서 
		
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/bis/{UID}", method = RequestMethod.GET)
	public ModelAndView mypageUser(@PathVariable("UID") Integer UID, UserDTO userdto, ModelAndView mav){
		Map<String, Object> map = userService.getInfo(UID);
		mav.addObject("map", map);
		mav.setViewName("mypage");
		return mav;
	}
	
	@RequestMapping(value = "/mypage/bis/update/{UID}", method = RequestMethod.POST)
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
