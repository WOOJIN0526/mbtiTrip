package com.example.test.replace.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

public interface ReplaceReviewService {

	 public ReplaceReviewDTO getPost(Integer replaceReviewID);
	 
	 public ReplaceReviewDTO create(String reviewTitle, String content, UserDTO user, ReplaceCategoryDTO category);
	 
	 public ReplaceReviewDTO modify(ReplaceReviewDTO rprDto, String reviewTitle, String content);
	 
	 public void delete(ReplaceReviewDTO rprDto);
	 
	 public ReplaceReviewDTO suggestion(ReplaceReviewDTO rprDto, UserDTO user);
	 
	 List<ReplaceReviewDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
