package com.example.test.replace.Service;

import java.util.List;


import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceCategoryDTO;
import com.example.test.replace.DTO.ReplaceDTO;

public interface ReplaceService {

	public ReplaceDTO getPost(Integer replaceID);
	 
	 public ReplaceDTO create(Integer postCategoryID, Integer mbtiID, Integer cityID, Integer replaceTypeId, 
			 String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel, UserDTO replaceAdmin, ReplaceCategoryDTO replace_CategoryID);
	 
	 public ReplaceDTO modify(ReplaceDTO rpDto, Integer postCategoryID,  Integer mbtiID, Integer cityID, 
			 Integer replaceTypeId, String replaceLocation, String replaceName, Integer replacePrice, String replaceContent, String tel);
	 
	 public void delete(ReplaceDTO rpDto);
	 
	 public ReplaceDTO suggestion(ReplaceDTO rpDto, UserDTO user);
	 
	 List<ReplaceDTO> list(Criteria cri) throws Exception;

	 public int listCount(Criteria cri) throws Exception;
}
