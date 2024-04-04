package com.example.test.HashTag;



import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public class TagDAO {

	public Tag findTagByContent(@Param("content") String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveTag(@Param("content") String tag) {
		// TODO Auto-generated method stub
		
	}



}
