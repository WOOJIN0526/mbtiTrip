package com.example.test.User.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("/mypage/cart")
@Log4j2
public class UserCartController {
	
	@Autowired
	UserCartService userCartservice;
	
	@RequestMapping(value = "",  method =RequestMethod.GET)
	public ModelAndView detail_Cart(Principal principal, UserCartDTO usercartDTO, ModelAndView mav) {		
		List<HashMap<String, Object>> userCart = userCartservice.detail(usercartDTO, principal);
		Integer sumPrice = userCartservice.sumPrice(userCart);
		log.info("message userCarts  => {}", userCart);
		mav.addObject("sumPrice", sumPrice);
		mav.addObject("userCarts", userCart);
		//mav.setViewName("Thtest");
		mav.setViewName("cart");
		return mav;
	}
	
	@RequestMapping(value = "/pay", method =RequestMethod.GET)
	public ModelAndView detail_Pay(Principal principal, UserCartDTO usercartDTO, ModelAndView mav) {
		List<HashMap<String, Object>> userCart = userCartservice.detail_Pay(usercartDTO, principal);
		log.info("userCartController {}", userCart.toString());
		mav.addObject("userCarts", userCart);
		mav.setViewName("Thtest");
		return mav;
	}
	

	@RequestMapping(value="/replace/input" , method=RequestMethod.POST)
	public ResponseEntity<?> insertReplace(@RequestBody ItemDTO itemDTO,
								@RequestBody UserCartDTO userCartDTO
								,Principal principal
								,ModelAndView mav){
		try {
			//장바구니에 item 넣기 
			boolean ck = userCartservice.insertItem(userCartDTO, itemDTO, principal);
		
		} catch (NullPointerException e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 정보를 찾을 수 없습니다.");
		}
		catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("정보가 정상적으로 저장되지 않았습니다.");
		}
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("정보가 정상적으로 저장되지 않았습니다.");
	}
	
	
	
	@RequestMapping(value="/adventure/input" , method=RequestMethod.POST)
	public boolean inserItem(@RequestBody ItemDTO itemDTO,
								@RequestBody UserCartDTO userCartDTO
								,Principal principal
								,ModelAndView mav) throws Exception{
		boolean ck = false;
			 ck = userCartservice.insertItem(userCartDTO, itemDTO, principal);
		return ck;
	}
	
	@RequestMapping(value="item/delte", method=RequestMethod.POST)
	public ResponseEntity<?> deleteItem(@RequestBody Integer itemId,
								Principal principal) {
		boolean ck = userCartservice.deleteItem(principal, itemId);
		//실패시 globalException Return 
		return ResponseEntity.status(HttpStatus.OK).body("장바구니에서 삭제되었습니다.");
	}
	
	@RequestMapping(value="/delte", method=RequestMethod.POST)
	public ResponseEntity<?> deleteALL(Principal principal) {
		boolean ck = userCartservice.deleteALL(principal);
		//실패시 globalException Return 
		return ResponseEntity.status(HttpStatus.OK).body("장바구니에서 삭제되었습니다.");
	}
	
	
	
	
}
