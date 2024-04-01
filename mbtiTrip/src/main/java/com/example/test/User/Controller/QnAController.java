package com.example.test.User.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.User.DTO.QnADTO;
import com.example.test.User.Service.QnAService;
import com.example.test.User.Service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/QnA")
@Log4j2
public class QnAController {

	
	@Autowired
	UserService userService; 
	
	@Autowired
	QnAService qnaService;
	
	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView QAndA(ModelAndView mav, QnADTO qna) {
		List<QnADTO> list = qnaService.getList(qna);
		log.info("{}", list.toString());
		mav.addObject("qnaList", list);
//		mav.addObject("QnAlist", QnAlist);
		mav.setViewName("customer_center");
		return mav;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createQAndA(ModelAndView mav) {
		mav.setViewName("qNa_form");
		return mav;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public boolean createQAndA(@RequestBody QnADTO qna,
								Principal princi) {
		boolean ck = false;
//		String userName = princi.getName();
//		Integer UID = userService.findByUID(userName);

		System.out.println(qna.toString());
//		qna.setUserName(userName);
//		qnaService.createQ(qna);
		return ck;
		
	}
	
	@RequestMapping(value = "/detail/{QID}", method = RequestMethod.GET)
	public ModelAndView QAndA(@PathVariable  Integer QID, ModelAndView mav,
							QnADTO qna) {
		QnADTO qnaDetail =qnaService.QnAdetail(QID);
		
		return mav;
	}
	
	@RequestMapping(value = "/myQnA", method= RequestMethod.GET)
	public ModelAndView myQnA(HttpServletRequest session , Principal prin, QnADTO qna, ModelAndView mav) {
		String userName = prin.getName();
		Map<String , Object> qnaList = qnaService.getMyQnA(userName);
		mav.addObject("currentUrl", session.getRequestURL());
		mav.addAllObjects(qnaList);
		mav.setViewName("내가 쓴 QnA");
		
		return mav;
		
	}
	
	
}
