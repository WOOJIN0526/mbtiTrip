package com.example.test.User.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.google.api.client.http.HttpResponse;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserCartServiceImpl implements UserCartService{

	
	@Autowired
	UserCartDAO userCartDAO;
	
	@Override
	public boolean insertItem(UserCartDTO userCartDTO,  ItemDTO ItemDTO, 
									Principal principal )throws Exception{
		//4.3 test끝 	
		//URL  = /replace/cart Post
		userCartDTO.setUserName(principal.getName());
		userCartDTO.setItemId(ItemDTO.getItemID());
		userCartDTO.setPayments(false);
		log.info("message {}", userCartDTO.toString());
		int result = 0;
		boolean ck = false;
		if(userCartDTO.getStartDate() == null) {
			throw new Exception("예약 날짜를 확인해주세요");
			}
		else if(userCartDTO.getEndDate() == null) {
			throw new Exception("예약 날짜를 확인해주세요");
			}
		else {
			 result = userCartDAO.insertItem(userCartDTO);
		}
		
		if(result ==1) {
			ck = true;
		}
		
		//controller에서 result가 0일 때 처리 + nullPointException 처리 
		return ck;
	}

	//수정 필요
	@Override
	public List<HashMap<String, Object>> detail(UserCartDTO usercartdto ,Principal principal) {
		//url = mypage/ myCart
		//payments get false;
		usercartdto.setUserName(principal.getName());
		List<HashMap<String, Object>> userCart = this.userCartDAO.detail(usercartdto);
		return userCart;
	}
	
	
	
	// 수정 필요
	@Override
	public Integer sumPrice(List<HashMap<String, Object>> userCart) {
		Integer result = 0;
		boolean replaceCk;
		boolean adventrueCk ;

		
		return result;
	}

	//수정 필요
	@Override
	public List<HashMap<String, Object>>  detail_Pay(UserCartDTO usercartdto ,Principal principal) {
		//url = mypage/ myPayments
		//payments get true;
		usercartdto.setUserName(principal.getName());
		List<HashMap<String, Object>> userCart = this.userCartDAO.detail_pay(usercartdto);
		return userCart;
	}

	@Override
	public String updatePaymentsSuccess(Principal prince) {
		String userName = prince.getName();
		String message = null;
		boolean ck = (userCartDAO.updatePaymentsSuccess(userName) == 1) ? true : false;
		if(ck) {
			message = "결제가 완료되었습니다";
		}
		return message;
	}

	@Override
	public String updatePaymentFalse(Principal prince) {
		String userName = prince.getName();
		String message = null;
		boolean ck = (userCartDAO.updatePaymentFalse(userName) == 1) ? true : false;
		if(ck) {
			message = "결제가 취소되었습니다";
		}
		return message;
	}

	@Override
	public boolean deleteItem(Principal principal, Integer itemID) {
		UserCartDTO userCart = new UserCartDTO();
		userCart.setUserName(principal.getName());
		userCart.setItemId(itemID);
		Integer result = userCartDAO.deleteItem(userCart);
		boolean ck = (result == 1) ? true : false; 		
		return ck;
	}


	@Override
	public boolean deleteALL(Principal principal) {
		UserCartDTO userCart = new UserCartDTO();
		userCart.setUserName(principal.getName());
		Integer result = userCartDAO.deleteALL(userCart);
		boolean ck = (result == 1) ? true : false; 		
		return ck;
	}

	
	//Exception 에러가 발생했을 떄, 처리할 method가 있으면 좋을 듯 
	// alter를  띄울 수 있는 method 필요 
}
