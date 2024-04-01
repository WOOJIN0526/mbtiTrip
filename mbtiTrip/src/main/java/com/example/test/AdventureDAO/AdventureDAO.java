package com.example.test.AdventureDAO;

import java.util.List;
import java.util.Map;

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
	
	public int insert(Map<String, Object> Adventure) {
		int result = this.sqlSessiontemplate.insert("adventure.insert",Adventure);
		return result;
	}

	public List<AdventureDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("adventure.getList", cri);
	}
	
	public List<AdventureDTO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("adventure.getListWithPaging", cri);
	}

	// 생성된 PK값을 알필요 없는경우
	public void insert(AdventureDTO ad) {
		sqlSessiontemplate.insert("adventure.insert", ad);
	}
	// 생성된 PK값을 알아야하는경우
	public Integer insertSelectKey(AdventureDTO ad) {
		return sqlSessiontemplate.insert("adventure.insertSelectKey", ad);
		
	}
	
	public AdventureDTO read(Long pno) {
		return sqlSessiontemplate.selectOne("adventure.read", pno);
	}
	
	public int delete(Long bno) {
		return sqlSessiontemplate.delete("adventure.delete", bno);
		
	}
	public int update(AdventureDTO ad) {
		return sqlSessiontemplate.update("adventure.update", ad);
	}
	
	//전체 데이터의 개수 처리(모든 게시물의 수)
	public int getTotalCount(Criteria cri) {
		return sqlSessiontemplate.selectOne("adventure.getTotalCount", cri);
	}
	
	//댓글이 등록되면 1이 증가, 삭제되면 1이 감소
	public void updateAnswerCnt(@Param("pno") Long pno, @Param("amount") int amount) {
		sqlSessiontemplate.update("adventure.updateAnswerCnt", amount);
	}
	
}
