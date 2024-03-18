package com.example.test.POST.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DTO.PostDTO;

@Service
public class PostServiceImpl implements PostService {

	@Autowired //Mapper와 연결
	private PostMapper postmapper;

	@Override
	public List<PostDTO> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return postmapper.selectBoardList();
	}
}
