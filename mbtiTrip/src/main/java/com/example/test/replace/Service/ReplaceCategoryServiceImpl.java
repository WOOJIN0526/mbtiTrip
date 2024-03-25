package com.example.test.replace.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.replace.DAO.ReplaceCategoryDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;

@Service
public class ReplaceCategoryServiceImpl implements ReplaceCategoryService{

	@Autowired
	ReplaceCategoryDAO rpcDAO;
	
	
	@Override
	public List<ReplaceCategoryDTO> getList() {
		// TODO Auto-generated method stub
		return rpcDAO.categoryName;
	}


	@Override
	public ReplaceCategoryDTO getCategory(String category) {
		Optional<ReplaceCategoryDTO> pc = this.rpcDAO.findById(category);
		if(pc.isPresent()) {
			return pc.get();
		}
		return null;
	}

	
}
