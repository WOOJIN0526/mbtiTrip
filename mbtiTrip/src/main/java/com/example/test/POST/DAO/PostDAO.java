package com.example.test.POST.DAO;



import java.util.List;
import java.util.Map;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.Post;

import com.example.test.POST.DTO.PostPage;




@Repository
public class PostDAO {

	@Autowired
	SqlSessionTemplate sqlSessiontemplate;
	
	public int insert(Map<String, Object> post) {
		int result = sqlSessiontemplate.insert("post.insert", post);
		return result;
	}

			// 게시글 목록 조회
			public List<Post> list() throws Exception {
				return sqlSessiontemplate.selectList("post_list");
			}
			
			// 게시글 쓰기
			public void register(Post post) throws Exception {
				sqlSessiontemplate.selectOne("post", post);
			}
			
			// 게시글 읽기
			public Post read(Integer postNo) throws Exception {
				return sqlSessiontemplate.selectOne("post_detail",postNo);
			}
			
			// 게시글 수정
			public void modify(Post post) throws Exception {
				sqlSessiontemplate.selectOne("post_list", post);
			}
			
			// 게시글 삭제
			public void remove(Integer postNo) throws Exception {
				sqlSessiontemplate.selectOne("post_detail", postNo);
			}
			
			// 게시글 검색
			public List<Post> search(String keyword) {
				return sqlSessiontemplate.selectOne("post_list",keyword);
			}
			
			// 전체 게시글 수
			public Integer totalCount() throws Exception {
				return sqlSessiontemplate.selectOne("post_list",totalCount());
			}
			
			// 전체 게시글 수
			public Integer totalCount(String keyword) throws Exception {
				return sqlSessiontemplate.selectOne("post_list", totalCount(keyword));
			}
			
			// [페이지] 게시글 목록
			public List<Post> list(PostPage page) throws Exception {
				return sqlSessiontemplate.selectOne("post_list", page);
			}
			
			// [검색어][페이지] 게시글 검색
			public List<Post> search(PostPage page) throws Exception {
				return sqlSessiontemplate.selectOne("post_list", page);
			}
			
			// 댓글 등록
			public void replyRegister(AnswerDTO answerDTO) throws Exception {
				sqlSessiontemplate.selectOne("post_detail",answerDTO);
			}
			
			// 댓글 목록 조회
			public List<AnswerDTO> replyList(Integer boardNo) throws Exception {
				return sqlSessiontemplate.selectOne("post_detail", boardNo);
			}
			
			// 댓글 수정 
			public void replyModify(AnswerDTO answerDTO) throws Exception {
				sqlSessiontemplate.selectOne("post_detail",answerDTO);
			}
			
			//댓글 삭제
			public void replyRemove(Integer reply_no) throws Exception {
				sqlSessiontemplate.selectOne("post_detail", reply_no);
			}
			
			
			// 그룹번호 수정
			public void updateGroupNo(Post post) throws Exception {
			}
			
			// 답글 쓰기
			public void answerRegister(Post post) throws Exception {
			}
			
			// 그룹번호 게시글 개수
			public int countAnswer(Integer groupNo) throws Exception {
				return sqlSessiontemplate.selectOne("post_list", groupNo);
			}
			
			// 계층번호 조회
			public int readDepthNo(Integer postNo) throws Exception {
				return sqlSessiontemplate.selectOne("post_list", postNo);
			}
			
			// 계층번호 조회
			public int readGroupNo(Integer postNo) throws Exception {
				return 0;
			}
			
			// 순서번호 MAX 조회
			public int maxSeqNo() throws Exception {
				return 0;
			}
			
			// 조회수 증가
			public void view(Integer postNo) throws Exception {
				
			}

			public void create(Post post) {
				// TODO Auto-generated method stub
				
			}

			public void update(Post post) {
				// TODO Auto-generated method stub
				sqlSessiontemplate.update("post_detail", post);
			}

			public void delete(Integer postNo) {
				// TODO Auto-generated method stub
				
			}

			public List<Post> listWithPage(PostPage page) {
				// TODO Auto-generated method stub
				return null;
			}

			public void answerCreate(Post post) {
				// TODO Auto-generated method stub
				
			}

			public void answerCreate(AnswerDTO answerDTO) {
				// TODO Auto-generated method stub
				
			}

			public void update(AnswerDTO answerDTO) {
				// TODO Auto-generated method stub
				
			}
	
	

	
	
}
