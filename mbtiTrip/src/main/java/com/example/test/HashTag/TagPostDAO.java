package com.example.test.HashTag;

import org.apache.ibatis.annotations.Param;

public class TagPostDAO {

	public Integer saveTagPost(@Param("tagId") Integer tagId, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Integer deleteTagPost(@Param("postId") Integer postId) {
		return postId;
		
	}
}
