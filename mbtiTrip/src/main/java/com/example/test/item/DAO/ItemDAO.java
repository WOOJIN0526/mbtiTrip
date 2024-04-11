package com.example.test.item.DAO;

import java.util.List;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.User.DAO.UserHistoryDAO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class ItemDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	@Autowired
	UserHistoryService userHistoryService;
	
	public int create(ItemDTO itemdto){
		 int result = this.sqlSessiontemplate.insert("item.create", itemdto);
		 log.info("message - > viewCreateItem 실행 전 ");
		 userHistoryService.ViewCreateItem();
		 return result;
	}
	
	public List<ItemDTO> replaceList(Criteria cri){
		List<ItemDTO> result = this.sqlSessiontemplate.selectList("item.replaceList", cri);
		 return result;
	}
	
	public List<ItemDTO> adventureList(Criteria criteria){
		List<ItemDTO> result = this.sqlSessiontemplate.selectList("item.adventure", criteria);
		return result;
	}
	
	public int myitem(String userName){
		int result = this.sqlSessiontemplate.selectOne("item.myitem", userName);
		return result;
	}
	
	public int itemByMbti(ItemDTO itemdto){
		int result = this.sqlSessiontemplate.selectOne("item.itemByMbti", itemdto);
		return result;
	}
	
	public int itemByLocation(ItemDTO itemdto){
		int result = this.sqlSessiontemplate.selectOne("item.itemByLocation", itemdto);
		return result;
	}
	
	public int mbtiAndLocation(ItemDTO itemdto){
		int result = this.sqlSessiontemplate.selectOne("item.mbtiAndLocation", itemdto);
		return result;
	}
	
	public int deleteItem(ItemDTO itemdto){
		  int result = this.sqlSessiontemplate.delete("item.deleteItem", itemdto);
		return result;
	}



	public Optional<ItemDTO> findById(Integer itemid) {
		// TODO Auto-generated method stub
		return this.sqlSessiontemplate.selectOne("item.findById", itemid);
	}

	public void updateCount(Integer itemID) {
		// TODO Auto-generated method stub
		this.sqlSessiontemplate.update("item.updateCount", itemID);
	}


	

	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("item.getTotal", cri);
	}

	public int update(ItemDTO itemdto) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.update("item.update", itemdto);
	}

	public int getLastInsertID() {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("item.getLastInsertId");
	}
}
