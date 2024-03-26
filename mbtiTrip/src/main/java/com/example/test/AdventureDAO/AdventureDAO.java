package com.example.test.AdventureDAO;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;

@Repository
public class AdventureDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure) {
		int result = this.sqlSessiontemplate.insert("adventure.insert",Adventure);
		return result;
	}

	public Page<AdventureDTO> findAll(Specification<AdventureDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<AdventureDTO> findById(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public AdventureDTO save(AdventureDTO adDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AdventureDTO adDto) {
		// TODO Auto-generated method stub
		
	}

	public void updateCount(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
