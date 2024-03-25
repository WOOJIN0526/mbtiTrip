package com.example.test.replace.DAO;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

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
	
	public int insert(Map<String, Object> replace) {
		int result = this.sqlSessiontemplate.insert("replace.insert",replace);
		return result;
	}

	public Page<ReplaceDTO> findAll(Specification<ReplaceDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<ReplaceDTO> findById(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReplaceDTO save(ReplaceDTO rpDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(ReplaceDTO rpDto) {
		// TODO Auto-generated method stub
		
	}
	
	 

//	
}
