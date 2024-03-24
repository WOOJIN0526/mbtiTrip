package com.example.test.replace.Service;

import org.springframework.data.domain.Page;


import com.example.test.replace.DTO.ReplaceReviewDTO;

public interface ReplaceReviewService {

	public Page<ReplaceReviewDTO> getList(int page, String kw, String categoryName);
	 
	 public ReplaceReviewDTO getPost(Integer userid);
	 
	 public ReplaceReviewDTO create(String title, String content, String user);
	 
	 public ReplaceReviewDTO modify(ReplaceReviewDTO rprDto, String title, String content);
	 
	 public void delete(ReplaceReviewDTO rprDto);
}
