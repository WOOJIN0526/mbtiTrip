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
		
        return postCategoryDao.categoryName;
	}

	@Override
	public Post_CategoryDTO getCategory(String category) {
		Optional<Post_CategoryDTO> pc = this.postCategoryDao.findById(category);
		if(pc.isPresent()) {
			return pc.get();
		}
		return null;
	}


	

	

	
}
