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
	
	public int insert(Map<String, Object> replace) {
		int result = this.sqlSessiontemplate.insert("replace.insert",replace);
		return result;
	}

	public List<ReplaceDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("replace.getList", cri);
	}
	
	public List<ReplaceDTO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("replace.getListWithPaging", cri);
	}

	// 생성된 PK값을 알필요 없는경우
	public void insert(ReplaceDTO ad) {
		sqlSessiontemplate.insert("replace.insert", ad);
	}
	// 생성된 PK값을 알아야하는경우
	public Integer insertSelectKey(ReplaceDTO ad) {
		return sqlSessiontemplate.insert("replace.insertSelectKey", ad);
		
	}
	
	public ReplaceDTO read(Long pno) {
		return sqlSessiontemplate.selectOne("replace.read", pno);
	}
	
	public int delete(Long bno) {
		return sqlSessiontemplate.delete("replace.delete", bno);
		
	}
	public int update(ReplaceDTO ad) {
		return sqlSessiontemplate.update("replace.update", ad);
	}
	
	//전체 데이터의 개수 처리(모든 게시물의 수)
	public int getTotalCount(Criteria cri) {
		return sqlSessiontemplate.selectOne("replace.getTotalCount", cri);
	}
	
	//댓글이 등록되면 1이 증가, 삭제되면 1이 감소
	public void updateAnswerCnt(@Param("pno") Long pno, @Param("amount") int amount) {
		sqlSessiontemplate.update("replace.updateAnswerCnt", amount);
	}
	
	 

	
}
