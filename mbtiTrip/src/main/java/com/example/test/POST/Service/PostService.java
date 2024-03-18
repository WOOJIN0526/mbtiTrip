package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.DTO.PostDTO;

public interface PostService {

	List<PostDTO> selectBoardList() throws Exception;
	
}
