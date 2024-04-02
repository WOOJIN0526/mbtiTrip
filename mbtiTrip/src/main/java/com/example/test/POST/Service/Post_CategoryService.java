package com.example.test.POST.Service;

import java.util.List;


import com.example.test.POST.DTO.Post_CategoryDTO;

public interface Post_CategoryService {

	public List<Post_CategoryDTO> getList();

	public Post_CategoryDTO getCategory(String category);

	

	

	
}
