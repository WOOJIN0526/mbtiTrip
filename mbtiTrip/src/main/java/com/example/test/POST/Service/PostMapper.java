package com.example.test.POST.Service;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test.POST.DTO.PostDTO;

@Mapper
public interface PostMapper {
	// 여기서 지정한 메서드의 이름은 쿼리의 이름과 동일해야 함 (selectBoardList)
	List<PostDTO> selectBoardList() throws Exception; 
}
