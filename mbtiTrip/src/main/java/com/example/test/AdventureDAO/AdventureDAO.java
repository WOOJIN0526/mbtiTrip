package com.example.test.AdventureDAO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.Criteria;

@Repository
public class AdventureDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> Adventure) {
		int result = this.sqlSessiontemplate.insert("adventure.insert",Adventure);
		return result;
	}

	public Optional<AdventureDTO> findById(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(AdventureDTO post) {
		// TODO Auto-generated method stub
		
	}

	public AdventureDTO save2(AdventureDTO adDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(AdventureDTO adDto) {
		// TODO Auto-generated method stub
		
	}

	public List<AdventureDTO> list(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("a_list", cri);
	}

	public int listCount(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectOne("a.listCount", cri);
	}

	@Modifying
	@Query("update Adventure p set p.count = p.count + 1 where p.id = :id")
	public void updateCount(Integer id) {
		sqlSessiontemplate.update("adventure.updateCount", id);
	}
	
}
