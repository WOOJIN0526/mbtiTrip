package com.example.test.HashTag;

import org.apache.ibatis.annotations.Param;

import com.example.test.POST.DTO.PostDTO;

public class TagPostDAO {

	public Integer saveTagPost(@Param("tagId") Integer tagId, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public PostDTO deleteTagPost(@Param("postId") PostDTO postDto) {
		return postDto;
		
	}
}
