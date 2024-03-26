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
		
		return null;
	}

	public Optional<ReplaceDTO> findById(Integer userid) {
		Optional<ReplaceDTO> result = this.sqlSessiontemplate.selectOne("replace.findById", userid);
		return result;
	}

	public ReplaceDTO save(ReplaceDTO rpDto) {
		ReplaceDTO result = sqlSessiontemplate.selectOne("replace.save", rpDto);
		return result;
	}

	public int delete(ReplaceDTO rpDto) {
		int result = sqlSessiontemplate.delete("replace.delete", rpDto);
		return result;
	}

	public int updateCount(Integer id) {
		int result = sqlSessiontemplate.update("replace.update", id);
		return result;
	}
	
	 

//	
}
