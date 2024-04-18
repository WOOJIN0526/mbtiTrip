package com.example.test.User.Controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.test.User.DTO.QAnswerDTO;
import com.example.test.User.DTO.QnADTO;
import com.example.test.User.Service.QnAService;
import com.example.test.User.Service.UserService;import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

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
		log.info("message QnA  main");
		List<QnADTO> list = qnaService.getList(qna);
		log.info("{}", list.toString());
		mav.addObject("qnaList", list);
//		mav.addObject("QnAlist", QnAlist);
		mav.setViewName("customer_center");
		return mav;
	} 
	
	@PreAuthorize("isAuthenticated() and  (hasRole('ROLE_USER') or hasRole('ROLE_BIS'))")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView createQAndA(Principal principal, ModelAndView mav) {
		log.info("create GEt ={}", principal.getName());
		mav.setViewName("qNa_form");
		return mav;
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public boolean createQAndA(@RequestBody QnADTO qna,
								Principal princi) {
		boolean ck = false;
		int  c =  qnaService.createQ(qna, princi);
		if(c == 1) {
			ck = true;
		} 
		log.info(ck); 
		return ck; 
	}
	
	
	@RequestMapping(value = "/detail/{QID}", method = RequestMethod.GET)
	public ModelAndView QAndA(@PathVariable  Integer QID, ModelAndView mav,
							QnADTO qna) {
		Map<String, Object> qnaDetail =qnaService.QnAdetail(QID);
		return mav;
	}
	
	
	@RequestMapping(value = "/myQnA", method= RequestMethod.GET)
	public ModelAndView myQnA(HttpServletRequest session , Principal prin, QnADTO qna, ModelAndView mav) {
		List<HashMap<String, Object>> qnaList = qnaService.getMyQnA(prin);
		mav.addObject("currentUrl", session.getRequestURL());
		mav.addObject("qnaList", qnaList);
		mav.setViewName("QAnswerTh");
		return mav;
	}
	
	@PreAuthorize("isAuthenticated() and  hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin", method=RequestMethod.GET)
	public ModelAndView adminQnA(Principal principal, ModelAndView mav) {
		String adminName = principal.getName();
		mav.addObject("adminName" , adminName);
		mav.setViewName("QnAAnswerCreateForm");
		return mav;
	}
	
	
	@RequestMapping(value="/admin", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> adminQnA(QAnswerDTO answer, Principal principal ) {
		boolean ck = qnaService.updateAnswer(answer, principal);
		return ResponseEntity.ok("답변이 성공적으로 등록되었습니다.");
	}

}
