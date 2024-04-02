package com.example.test.Adventure.Service;

import java.util.List;

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;



public interface Adventure_CategoryService {

	public List<Adventure_CategoryDTO> getList();

	public Adventure_CategoryDTO getCategory(Integer integer);

	

}
