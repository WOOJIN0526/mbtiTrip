package com.example.test.replace.Service;


import java.security.Principal;
import java.util.List;


import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Page;
import com.example.testExcepion.GCSS.GCSSException;


public interface ReplaceService {

		//게시글 목록 조회
		public List<ItemDTO> list(Page page) throws Exception;

		//게시글 쓰기
		public void create(ItemDTO post) throws Exception;

		//게시글 읽기
		public ItemDTO getPost(Integer itemId, Principal principal) throws Exception;

		//게시글 수정
		public void  modify(ItemDTO post) throws Exception;

		//게시글 삭제
		public void remove(Integer itemId) throws Exception;

		//게시글 검색
		public List<ItemDTO> search(String keyword);
		
		public List<ItemDTO> search(Page page) throws Exception;

		//전체 게시글 수
		public Integer totalCount() throws Exception;


	 
	 
	public List<String> getUrls(int itemID);

	public int createImg(ItemDTO itemdto);

	public int suggestion(Integer itemID, Principal principal)throws Exception;


	 



}
