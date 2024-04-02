package com.example.test.replace.Service;

import java.util.List;


import com.example.test.replace.DTO.ReplaceCategoryDTO;

public interface ReplaceCategoryService {


	
	
	public List<ReplaceCategoryDTO> getList();

	public ReplaceCategoryDTO getCategory(Integer replaceCategoryDTO);

	
}
