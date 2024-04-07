package com.example.test.User.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.Service.UserCartService;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("user/history")
public class userHistoryController {

	
	/* 총 4개의 정보를 전달 해야 함. 
	 * 예약된 replace
	 *  //  adventure
	 * 내가 쓴 글 
	 * 내가 문의한 사항 
	 * */
	
	@Autowired
	UserCartService userCartService;
	
	@Autowired
	UserHistoryService userHistoryService;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView userHistory(Principal principal, ModelAndView mav,
									UserCartDTO userCartDTO) {
		List<UserCartDTO> reservation =userCartService.detail_Pay(userCartDTO, principal);
		List<HashMap<String, Object>> userPost = userHistoryService.selectUserPost(principal);
		List<HashMap<String, Object>> userQnA = userHistoryService.selectUserQnA(principal);
		mav.addObject("reservation", reservation);
		mav.addObject("userPosts", userPost);
		mav.addObject("userQnAs", userQnA);
		mav.setViewName("testHistory");
		return mav;
	}
	
	
	//history 주제별 삭제 기능 
	@RequestMapping(value="delete/replace", method=RequestMethod.POST)
	public boolean userHistoryReplaceDelet(@RequestBody Integer replaceId,
											Principal principal) {
		ReplaceDTO replace = new ReplaceDTO();
		replace.setReplaceID(replaceId);
		boolean ck = userCartService.deleteReplace(principal, replace);	
		return ck;
	}
	@RequestMapping(value="delete/adventure", method=RequestMethod.POST)
	public boolean userHistoryADDelet(@RequestBody Integer adventuredID,
											Principal principal) {
		AdventureDTO ad = new AdventureDTO();
		ad.setAdventureID(adventuredID);
		boolean ck = userCartService.deleteAD(principal, ad);	
		return ck;
	}
	@RequestMapping(value="delete/ALL", method=RequestMethod.POST)
	public boolean userHistoryAllDelet(Principal principal) {
		boolean ck = userCartService.deleteALL(principal);	
		return ck;
	}
}
