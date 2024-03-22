package com.example.test.Adventure.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.Adventure_CategoryDTO;
import com.example.test.AdventureDAO.Adventure_CategoryDAO;

@Service
public class Adventure_CategoryServiceImpl implements Adventure_CategoryService {

	@Autowired
	Adventure_CategoryDAO adcDAO;

	@Override
	public List<Adventure_CategoryDTO> getList() {
		// TODO Auto-generated method stub
		return adcDAO.categoryName;
	}
	
	
	
}
