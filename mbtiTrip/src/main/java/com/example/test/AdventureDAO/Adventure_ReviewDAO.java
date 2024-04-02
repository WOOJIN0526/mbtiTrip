package com.example.test.AdventureDAO;

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

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;

@Repository
public class Adventure_ReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure_Review) {
		int result = this.sqlSessiontemplate.insert("adventure_review.insert",Adventure_Review);
		return result;
	}

	public Optional<Adventure_ReviewDTO> findById(Integer adventureReviewID) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("adventure_review.findById", adventureReviewID);
	}

	public Adventure_ReviewDTO save(Adventure_ReviewDTO adrDto) {
		return adrDto;
		// TODO Auto-generated method stub
		
	}

	public void delete(Adventure_ReviewDTO adrDto) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.delete("adventure_review.delete", adrDto);
	}

	public List<Adventure_ReviewDTO> list(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("adventure_review.list", cri);
	}

	public int listCount(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("adventure_review.listCount", cri);
	}

	public void updateCount(Integer adventureReviewID) {
		// TODO Auto-generated method stub
		sqlSessiontemplate.update("adventure_review.updateCount", adventureReviewID);
	}

	
}
