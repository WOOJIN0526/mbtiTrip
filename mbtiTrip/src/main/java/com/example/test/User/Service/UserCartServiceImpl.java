package com.example.test.User.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DAO.UserCartDAO;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

import com.example.testExcepion.Cart.CartException;
import com.example.testExcepion.Cart.CartExceptionEnum;
import com.example.testExcepion.Insert.InsertException;
import com.example.testExcepion.Insert.InsertExceptionEnum;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.storage.Storage.BucketAccessControls.Insert;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class UserCartServiceImpl implements UserCartService{

	
	@Autowired
	UserCartDAO userCartDAO;
	
	@Override
	public boolean insertItem(UserCartDTO userCartDTO,  ItemDTO ItemDTO, 
									Principal principal) throws CartException{
		//4.3 test끝 	
		//URL  = /replace/cart Post
		int result = 0;
		boolean ck;
		
		if(principal.getName() == null) {
			throw new CartException(CartExceptionEnum.CART_NOTFOUND_USER);
		}
		
		if(userCartDTO.getItemId() == null) {
			throw new CartException(CartExceptionEnum.CART_NOTFOUND_ITEM);
		}
		
		if(userCartDTO.getStartDate() == null) {
			throw new CartException(CartExceptionEnum.CART_STARTDATE_NULL);
			}
		
		if(userCartDTO.getEndDate() == null) {
			throw new CartException(CartExceptionEnum.CART_ENDDATE_NULL);
			}
		
		if(userCartDTO.getStartDate().isAfter(LocalDate.now())){
			throw new CartException(CartExceptionEnum.CART_STARTDATE_MISMATCH);
		}
		
	    if(userCartDTO.getEndDate().isBefore(userCartDTO.getStartDate())) {
			throw new CartException(CartExceptionEnum.CART_ENDdDATE_MISMATCH);
		}
	    
		userCartDTO.setUserName(principal.getName());
		userCartDTO.setItemId(ItemDTO.getItemID());
		userCartDTO.setPayments(false);
		log.info("message {}", userCartDTO.toString());
		result = userCartDAO.insertItem(userCartDTO);	
			
		if(result == 0) {
			throw new InsertException(InsertExceptionEnum.INSERT_SERVER_ERROR);
		}
		
		ck = (result != 0) ? true : false;
		
		return ck;
	}

	//수정 필요
	@Override
	public List<HashMap<String, Object>> detail(UserCartDTO usercartdto ,Principal principal) {
		//url = mypage/ myCart
		//payments get false;
		usercartdto.setUserName(principal.getName());
		List<HashMap<String, Object>> userCart = this.userCartDAO.detail(usercartdto);
		usercartdto.setFinalPrice(sumPrice(userCart));
		return userCart;
	}
	
	
	
	// 수정 필요
	@Override
	public Integer sumPrice(List<HashMap<String, Object>> userCart) {
		Integer result = 0;
		for(HashMap<String, Object> item : userCart) {
			result += (int) item.get("price");
		}		
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
	public boolean updatePaymentsSuccess(Principal prince) throws CartException {
		String userName = prince.getName();
		boolean ck = (userCartDAO.updatePaymentsSuccess(userName) != 0) ? true : false ;
		if(!ck) {
			throw new CartException(CartExceptionEnum.PAYMENTS_FAIL);
		}
		//ck가 false일 때 예오ㅓㅣ처리
		return ck;
	}

	@Override
	public boolean updatePaymentFalse(Principal prince)throws CartException {
		String userName = prince.getName();
		boolean ck = (userCartDAO.updatePaymentFalse(userName) == 1) ? true : false;
		//ck가 false일 때 예오ㅓㅣ처리
		if(!ck) {
			throw new CartException(CartExceptionEnum.PAYMENTS_DROPFAIL);
		}
		return ck;
	}

	@Override
	public boolean deleteItem(Principal principal, Integer itemID) {
		UserCartDTO userCart = new UserCartDTO();
		userCart.setUserName(principal.getName());
		userCart.setItemId(itemID);
		Integer result = userCartDAO.deleteItem(userCart);
		boolean ck = (result == 1) ? true : false; 		
		//ck가 false일 때 예오ㅓㅣ처리
		return ck;
	}


	@Override
	public boolean deleteALL(Principal principal) {
		UserCartDTO userCart = new UserCartDTO();
		userCart.setUserName(principal.getName());
		Integer result = userCartDAO.deleteALL(userCart);
		boolean ck = (result == 1) ? true : false; 		
		//ck가 false일 때 예오ㅓㅣ처리 
		return ck;
	}

	
	@Override
	public List<HashMap<String, Object>> reservationInfo(Principal principal) {
		String adminName = principal.getName();
		List<HashMap<String, Object>> userReservationInfo 
					= userCartDAO.reservationInfo(adminName);
		return userReservationInfo;
	}

	
	//Exception 에러가 발생했을 떄, 처리할 method가 있으면 좋을 듯 
	// alter를  띄울 수 있는 method 필요 
}
