package com.example.test.User.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.User.Service.UserCartService;
import com.example.test.User.Service.UserService;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/mypage/cart")
@Log4j2
public class UserCartController {
	

	UserCartService userCartservice;
	
	@RequestMapping("")
	public ModelAndView detail_Cart(Principal principal, UserCartDTO usercartDTO, ModelAndView mav) {		
		List<UserCartDTO> userCart = userCartservice.detail(usercartDTO, principal);
		Integer sumPrice = userCartservice.sumPrice(userCart);
		mav.addObject("sumPrice", sumPrice);
		mav.addObject("userCarts", userCart);
		mav.setViewName("Mycart_info");
		return mav;
	}
	
	@RequestMapping("/pay")
	public ModelAndView detail_Pay(Principal principal, UserCartDTO usercartDTO, ModelAndView mav) {
		List<UserCartDTO> userCart = userCartservice.detail_Pay(usercartDTO, principal);
		log.info("userCartController {}", userCart.toString());
		mav.addObject("userCarts", userCart);
		mav.setViewName("Mycart_info");
		return mav;
	}
	

	@RequestMapping(value="/replace/input" , method=RequestMethod.POST)
	public boolean insertReplace(@RequestBody ReplaceDTO replaceDTO,
								@RequestBody UserCartDTO userCartDTO
								,Principal principal
								,ModelAndView mav){
		boolean ck = false;
		try {
			 ck = userCartservice.insertReplace(userCartDTO, replaceDTO, principal);
		} catch (NullPointerException e) {
			mav.addObject("error", e.getMessage());
		}
		catch (Exception e) {
			mav.addObject("error", e.getMessage());
		}
		return  ck;
	}
	
	@RequestMapping(value="/adventure/input" , method=RequestMethod.POST)
	public boolean insertReplace(@RequestBody AdventureDTO adventureDTO,
								@RequestBody UserCartDTO userCartDTO
								,Principal principal
								,ModelAndView mav){
		boolean ck = false;
		try {
			 ck = userCartservice.insertAD(userCartDTO, adventureDTO, principal);
		} catch (NullPointerException e) {
			mav.addObject("error", e.getMessage());
		}
		catch (Exception e) {
			mav.addObject("error", e.getMessage());
		}
		return  ck;
	}
	
	@RequestMapping(value="replace/delte", method=RequestMethod.POST)
	public boolean deleteReplace(@RequestBody ReplaceDTO replace,
								Principal principal) {
		boolean ck = userCartservice.deleteReplace(principal, replace);
		return ck;
	}
	
	@RequestMapping(value="replace/delte", method=RequestMethod.POST)
	public boolean deleteReplace(@RequestBody AdventureDTO adventure, Principal principal) {
		boolean ck = userCartservice.deleteAD(principal, adventure);
		return ck;
	}
	@RequestMapping(value="replace/delte", method=RequestMethod.POST)
	public boolean deleteALL(Principal principal) {
		boolean ck = userCartservice.deleteALL(principal);
		return ck;
	}
	
	
	
	
}
