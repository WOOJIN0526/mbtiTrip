package com.example.test.User.DAO;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.test.User.DTO.UserCartDTO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class UserCartDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	//insert하는 정보에 따른 분할
	public int insertItem(UserCartDTO userCartDTO) {
		int result = sqlSessiontemplate.insert("userCart.insertItem", userCartDTO);
		return result;
	}
	

	//사용자에게 보여 질 userCart
	public List<HashMap<String, Object>> detail(UserCartDTO userCartDTO){
		//payments가 false인경우만 조회 
		List<HashMap<String, Object>> userCart = this.sqlSessiontemplate.selectList("userCart.detail", userCartDTO);
		log.info("DAO ===> userCart Detail_Pay {}", userCart);
		return userCart;
	}
	

	//결제 완료된 내역 eq 예약 된 정보 
	public List<HashMap<String, Object>> detail_pay(UserCartDTO userCartDTO){
		List<HashMap<String, Object>> userCart = this.sqlSessiontemplate.selectList("userCart.detailPay", userCartDTO);
		log.info("messsage ===> userCart Detail_Pay {}", userCart);
		return userCart;
	}

	//결제 여부 
	public int updatePaymentsSuccess(String userName) {
		//payment를 true로 바꾸는 method  
		//성공 시 userpayment or history에 등록
		int result = this.sqlSessiontemplate.update("userCart.updatePaymentsSuccess", userName);
		return result;
	}
	
	public int updatePaymentFalse(String userName) {
		int result = this.sqlSessiontemplate.update("userCart.updatePaymentFalse", userName);
		return result;
	}
	
	
	
	public int deleteItem(UserCartDTO userCart) {
		int result = this.sqlSessiontemplate.delete("userCart.deletRE", userCart);
		return result;
	}
	
	public int deleteALL(UserCartDTO userCart) {
		int result = this.sqlSessiontemplate.delete("userCart.deletRE", userCart);
		return result;
	}
	
	
	
	
	//추가적 기능 구현 enddate가 지났을 경우, history로 이관작업
	
	
//	@Id
//	@GeneratedValue
//	@Column(name = "UserCart_ID")
//	private Integer id;
//	
//	private String author;
//	
//	private Integer price;
//	
//	
//	@Column(name = "UCRE_ID")
//	private Integer ucreID;
//	//UserCartRePlace
//	
//	
//	@Column(name = "UCAD_ID")
//	private Integer ucadID;
//	//UserCartAdventrud
//	
//	private LocalDateTime updateDate;
}

