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

	public List<Adventure_ReviewDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("adventure_review.getList", cri);
	}
	
	public List<Adventure_ReviewDTO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("adventure_review.getListWithPaging", cri);
	}

	// 생성된 PK값을 알필요 없는경우
	public void insert(Adventure_ReviewDTO adr) {
		sqlSessiontemplate.insert("adventure_review.insert", adr);
	}
	// 생성된 PK값을 알아야하는경우
	public Integer insertSelectKey(Adventure_ReviewDTO adr) {
		return sqlSessiontemplate.insert("adventure_review.insertSelectKey", adr);
		
	}
	
	public Adventure_ReviewDTO read(Long pno) {
		return sqlSessiontemplate.selectOne("adventure_review.read", pno);
	}
	
	public int delete(Long bno) {
		return sqlSessiontemplate.delete("adventure_review.delete", bno);
		
	}
	public int update(Adventure_ReviewDTO adr) {
		return sqlSessiontemplate.update("adventure_review.update", adr);
	}
	
	//전체 데이터의 개수 처리(모든 게시물의 수)
	public int getTotalCount(Criteria cri) {
		return sqlSessiontemplate.selectOne("adventure_review.getTotalCount", cri);
	}
	
	//댓글이 등록되면 1이 증가, 삭제되면 1이 감소
	public void updateAnswerCnt(@Param("pno") Long pno, @Param("amount") int amount) {
		sqlSessiontemplate.update("adventure_review.updateAnswerCnt", amount);
	}
}
