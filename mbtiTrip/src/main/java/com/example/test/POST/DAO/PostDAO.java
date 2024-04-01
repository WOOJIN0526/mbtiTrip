package com.example.test.POST.DAO;







import java.util.List;
import java.util.Map;


import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Repository;


import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;





@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

	
	public void insert(PostDTO post) {
		sqlSessiontemplate.insert("post.insert", post);
	}
	
	public Integer insertSelectKey(PostDTO post) {
		return sqlSessiontemplate.insert("post.insertSelectKey", post);
		
	}
	
	public PostDTO read(Long pno) {
		return sqlSessiontemplate.selectOne("post.read", pno);
	}
	
	public int delete(Long bno) {
		return sqlSessiontemplate.delete("post.delete", bno);
		
	}
	public int update(PostDTO post) {
		return sqlSessiontemplate.update("post.update", post);
	}
	
	//전체 데이터의 개수 처리(모든 게시물의 수)
	public int getTotalCount(Criteria cri) {
		return sqlSessiontemplate.selectOne("post.getTotalCount", cri);
	}
	
	//댓글이 등록되면 1이 증가, 삭제되면 1이 감소
	public void updateAnswerCnt(@Param("pno") Long pno, @Param("amount") int amount) {
		sqlSessiontemplate.update("post.updateAnswerCnt", amount);
	}


	public List<PostDTO> getListWithPaging(Criteria cri) {
		// TODO Auto-generated method stub
		return sqlSessiontemplate.selectList("post.getListWithPaging", cri);
	}
	





	
	
	
}
