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

import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;


@Repository
public class ReplaceReviewDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate ;
	
	public int insert(Map<String, Object> replaceCategory) {
		int result = this.sqlSessiontemplate.insert("replaceCategory.insert",replaceCategory);
		return result;
	}

	public List<ReplaceReviewDTO> getList(Criteria cri){
		return sqlSessiontemplate.selectList("replace_review.getList", cri);
	}
	
	public List<ReplaceReviewDTO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("replace_review.getListWithPaging", cri);
	}

	// 생성된 PK값을 알필요 없는경우
	public void insert(ReplaceReviewDTO ad) {
		sqlSessiontemplate.insert("replace_review.insert", ad);
	}
	// 생성된 PK값을 알아야하는경우
	public Integer insertSelectKey(ReplaceReviewDTO ad) {
		return sqlSessiontemplate.insert("replace_review.insertSelectKey", ad);
		
	}
	
	public ReplaceReviewDTO read(Long pno) {
		return sqlSessiontemplate.selectOne("replace_review.read", pno);
	}
	
	public int delete(Long bno) {
		return sqlSessiontemplate.delete("replace_review.delete", bno);
		
	}
	public int update(ReplaceReviewDTO ad) {
		return sqlSessiontemplate.update("replace_review.update", ad);
	}
	
	//전체 데이터의 개수 처리(모든 게시물의 수)
	public int getTotalCount(Criteria cri) {
		return sqlSessiontemplate.selectOne("replace_review.getTotalCount", cri);
	}
	
	//댓글이 등록되면 1이 증가, 삭제되면 1이 감소
	public void updateAnswerCnt(@Param("pno") Long pno, @Param("amount") int amount) {
		sqlSessiontemplate.update("replace.updateAnswerCnt", amount);
	}
	
	
	

}
