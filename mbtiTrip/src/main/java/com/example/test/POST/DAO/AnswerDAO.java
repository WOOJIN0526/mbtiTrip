package com.example.test.POST.DAO;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.paging.Criteria;



@Repository
public class AnswerDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> answer) {
		int result = sqlSessiontemplate.insert("answer.insert", answer);
		return result;
	}

	//등록
	public int insert(AnswerDTO answer) {
		return sqlSessiontemplate.insert("answer.insert", answer);
	}
	
	//조회(특정 댓글 읽기)
	public AnswerDTO read(Long ano) {
		return sqlSessiontemplate.selectOne("answer.read", ano);
	}
	
	
	//특정댓글삭제
	public int delete(Long ano) {
		return sqlSessiontemplate.delete("answer.delete", ano);
	}
	
	//수정
	public int update(AnswerDTO answer) {
		return sqlSessiontemplate.update("answer.update", answer);
	}
	
	//댓글목록 페이징처리
	public List<AnswerDTO> getListWithPaging(@Param("cri") Criteria cri, @Param("pno") Long pno){
		return sqlSessiontemplate.selectList("answer.getListWithPaging", cri);
	}
	
	//해당 게시물의 댓글수 파악
	public int getCountByPno(Long pno) {
		return sqlSessiontemplate.selectOne("answer.getCountByPno", pno);
	}
}
