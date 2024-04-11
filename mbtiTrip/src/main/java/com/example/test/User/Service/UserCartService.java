package com.example.test.User.Service;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface UserCartService {

	public boolean insertItem(UserCartDTO userCartDTO,  ItemDTO ItemDTO, Principal principal)throws Exception;

	//예약 전 내역
	public List<HashMap<String, Object>>  detail(UserCartDTO usercartdto ,Principal principal);
	
	//예약 된 내역
	public List<HashMap<String, Object>>  detail_Pay(UserCartDTO usercartdto ,Principal principal);

	//결제에서 호출 될 method
	public boolean updatePaymentsSuccess(Principal prince);

	//예약 취소 
	public boolean updatePaymentFalse(Principal prince);

	Integer sumPrice(List<HashMap<String, Object>> userCart);

	//BIS 예약 된 내역 조희 
	public List<HashMap<String, Object>> reservationInfo(Principal principal);
	
	
	//삭제 기능 
	public boolean deleteItem(Principal principal, Integer itemId);
	public boolean deleteALL(Principal principal);
	
}
