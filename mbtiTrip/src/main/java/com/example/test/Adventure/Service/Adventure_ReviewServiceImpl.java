package com.example.test.Adventure.Service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.AdventureDAO.Adventure_ReviewDAO;

import com.example.test.paging.Criteria;



@Service
public class Adventure_ReviewServiceImpl implements Adventure_ReviewService {

	@Autowired
	Adventure_ReviewDAO adrDAO;

	@Override
	public void register(Adventure_ReviewDTO adr) {
		// TODO Auto-generated method stub
		adrDAO.insertSelectKey(adr);
	}

	@Override
	public Adventure_ReviewDTO get(Long pno) {
		// TODO Auto-generated method stub
		return adrDAO.read(pno);
	}

	@Override
	public boolean modify(Adventure_ReviewDTO adr) {
		// TODO Auto-generated method stub
		return adrDAO.update(adr) == 1;//수정, 삭제가 이루어지면 1이라는 값이 반환되서 true/false처리가능
	}

	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return adrDAO.delete(pno) == 1;
	}

	@Override
	public List<Adventure_ReviewDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return adrDAO.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return adrDAO.getTotalCount(cri);
	}
	
	
	
}
