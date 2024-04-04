package com.example.test.User.Service;

import java.security.Principal;
import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserCartDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface UserCartService {

	public boolean insertReplace(UserCartDTO userCartDTO,ReplaceDTO replaceDTO, Principal principal) throws Exception;
	
	//어드벤처 추가 
	public boolean insertAD(UserCartDTO userCartDTO, AdventureDTO adventureDTO, Principal principal) throws Exception;
	
	//예약 전 내역
	public List<UserCartDTO> detail(UserCartDTO usercartdto ,Principal principal);
	
	//예약 된 내역
	public List<UserCartDTO> detail_Pay(UserCartDTO usercartdto ,Principal principal);

	//결제에서 호출 될 method
	public String updatePaymentsSuccess(Principal prince);

	//예약 취소 
	public String updatePaymentFalse(Principal prince);
}
