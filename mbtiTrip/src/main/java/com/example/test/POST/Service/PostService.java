package com.example.test.POST.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;


public interface PostService {

	 	 //게시글 목록조회
		 public List<PostDTO> getList(Criteria criteria);
		 
		 //전체 게시글 수
		 public int getTotal(Criteria cri);
	 
		 //게시글 읽기
		 public PostDTO getPost(Integer postID);
		 
		 //게시글 쓰기
		 public void create(PostDTO post);
		 
		 //게시글 수정
		 public void modify(PostDTO post);
		 
		 //게시글 삭제
		 public void delete(PostDTO post);
		 
		 //게시글 추천
		 public Integer suggestion(PostDTO post, UserDTO userDto);
		 
		

		public List<PostDTO> findPostByCategoryID(Long postCategoryID);

		
		
}
