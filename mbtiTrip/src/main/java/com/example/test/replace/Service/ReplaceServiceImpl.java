package com.example.test.replace.Service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.test.paging.Criteria;
import com.example.test.replace.DAO.ReplaceDAO;
import com.example.test.replace.DTO.ReplaceDTO;



@Service
public class ReplaceServiceImpl implements ReplaceService{

	@Autowired
	ReplaceDAO rpDAO;

	@Override
	public void register(ReplaceDTO ad) {
		// TODO Auto-generated method stub
		rpDAO.insertSelectKey(ad);
	}

	@Override
	public ReplaceDTO get(Long pno) {
		// TODO Auto-generated method stub
		return rpDAO.read(pno);
	}

	@Override
	public boolean modify(ReplaceDTO ad) {
		// TODO Auto-generated method stub
		return rpDAO.update(ad) == 1;//수정, 삭제가 이루어지면 1이라는 값이 반환되서 true/false처리가능
	}

	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return rpDAO.delete(pno) == 1;
	}

	@Override
	public List<ReplaceDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return rpDAO.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return rpDAO.getTotalCount(cri);
	}

	
	
	
	
}
