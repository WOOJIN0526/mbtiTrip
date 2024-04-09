package com.example.test.replace.DAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceDTO;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Repository
public class ReplaceDAO {

	
	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
//	public int insert(Map<String, Object> replace) {
//		int result = this.sqlSessiontemplate.insert("replace.insert",replace);
//		return result;
//	}

//	public Optional<ReplaceDTO> findById(Integer replaceID) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectOne("replace.findById", replaceID);
//	}
//
//	public ReplaceDTO save(ReplaceDTO rpDto) {
//		return rpDto;
//		// TODO Auto-generated method stub
//		
//	}
//
//	public void delete(ReplaceDTO rpDto) {
//		// TODO Auto-generated method stub
//		sqlSessiontemplate.delete("replace.delete", rpDto);
//	}
//
//	public List<ReplaceDTO> list(Criteria cri) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectList("replace.list", cri);
//	}
//
//	public int listCount(Criteria cri) {
//		// TODO Auto-generated method stub
//		return sqlSessiontemplate.selectOne("replace.listCount", cri);
//	}
//
//	public void updateCount(Integer replaceID) {
//		// TODO Auto-generated method stub
//		sqlSessiontemplate.update("replace.updateCount", replaceID);
//	}

	
	
	 

	
}
