package com.example.test.Adventure.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.AdventureDAO.AdventureDAO;
import com.example.test.paging.Criteria;

@Service
public class AdventureServiceImpl implements AdventureService{

	@Autowired
	AdventureDAO adDAO;

	@Override
	public void register(AdventureDTO ad) {
		// TODO Auto-generated method stub
		adDAO.insertSelectKey(ad);
	}

	@Override
	public AdventureDTO get(Long pno) {
		// TODO Auto-generated method stub
		return adDAO.read(pno);
	}

	@Override
	public boolean modify(AdventureDTO ad) {
		// TODO Auto-generated method stub
		return adDAO.update(ad) == 1;//수정, 삭제가 이루어지면 1이라는 값이 반환되서 true/false처리가능
	}

	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return adDAO.delete(pno) == 1;
	}

	@Override
	public List<AdventureDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return adDAO.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return adDAO.getTotalCount(cri);
	}

	
}
