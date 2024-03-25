package com.example.test.Adventure.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.AdventureDAO.Adventure_CategoryDAO;
import com.example.test.POST.DTO.Post_CategoryDTO;

@Service
public class Adventure_CategoryServiceImpl implements Adventure_CategoryService {

	@Autowired
	Adventure_CategoryDAO adcDAO;

	@Override
	public List<Adventure_CategoryDTO> getList() {
		// TODO Auto-generated method stub
		return adcDAO.categoryName;
	}

	@Override
	public Adventure_CategoryDTO getCategory(String category) {
		Optional<Adventure_CategoryDTO> pc = this.adcDAO.findById(category);
		if(pc.isPresent()) {
			return pc.get();
		}
		return null;
	}
	
	
	
}
