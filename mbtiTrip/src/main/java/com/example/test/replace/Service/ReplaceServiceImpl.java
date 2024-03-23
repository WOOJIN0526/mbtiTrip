package com.example.test.replace.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DAO.ReplaceDAO;
import com.example.test.replace.DTO.ReplaceCategoryDTO;

@Service
public class ReplaceServiceImpl implements ReplaceService{

	@Autowired
	ReplaceDAO rpDAO;

	@Override
	public Page<ReplaceCategoryDTO> getList(int page, String kw, String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplaceCategoryDTO getPost(Integer userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplaceCategoryDTO create(String title, String content, String admin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReplaceCategoryDTO modify(ReplaceCategoryDTO adDto, String title, String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ReplaceCategoryDTO adDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ReplaceCategoryDTO vote(ReplaceCategoryDTO adDto, UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
