package com.example.test.AdventureDAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.paging.Criteria;

@Repository
public class AdventureDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
//	public int insert(Map<String, Object> Adventure) {
//		int result = this.sqlSessiontemplate.insert("adventure.insert",Adventure);
//		return result;
//	}

//	public Optional<AdventureDTO> findById(Integer adventureID) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectOne("adventure.findById", adventureID);
//	}
//
//	public AdventureDTO save(AdventureDTO adDto) {
//		return adDto;
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void delete(AdventureDTO adDto) {
//		sqlSessiontemplate.delete("adventure.delete", adDto);
//		
//	}
//
//	public List<AdventureDTO> list(Criteria cri) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectList("adventure.list", cri);
//	}
//
//	public int listCount(Criteria cri) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectOne("adventure.listCount", cri);
//	}
//
//	public void updateCount(Integer adventureID) {
//		// TODO Auto-generated method stub
//		sqlSessiontemplate.update("adventure.updateCount", adventureID);
//	}

	
	
}
