package com.example.test.AdventureDAO;

import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;

@Repository
public class Adventure_ReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure_Review) {
		int result = this.sqlSessiontemplate.insert("adventure_review.insert",Adventure_Review);
		return result;
	}

	public Page<Adventure_ReviewDTO> findAll(Specification<Adventure_ReviewDTO> spec, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public Optional<Adventure_ReviewDTO> findById(Integer reviewid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Adventure_ReviewDTO save(Adventure_ReviewDTO adrDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(Adventure_ReviewDTO adrDto) {
		// TODO Auto-generated method stub
		
	}

	public void updateCount(Integer id) {
		// TODO Auto-generated method stub
		
	}
}
