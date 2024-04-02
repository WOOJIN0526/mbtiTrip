package com.example.test.POST.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.Post_CategoryDAO;
import com.example.test.POST.DTO.Post_CategoryDTO;

@Service
public class Post_CategoryServiceImpl implements Post_CategoryService {

	@Autowired
	Post_CategoryDAO postCategoryDao;

	@Override
	public List<Post_CategoryDTO> getList() {
		// TODO Auto-generated method stub
		return this.postCategoryDao.getList();
	}

	@Override
	public Post_CategoryDTO getCategory(String category) {
		// TODO Auto-generated method stub
		return this.postCategoryDao.getCategory(category);
	}
	



	

	

	
}
