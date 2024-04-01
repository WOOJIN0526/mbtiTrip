package com.example.test.replace.Service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.test.paging.Criteria;
import com.example.test.replace.DAO.ReplaceReviewDAO;
import com.example.test.replace.DTO.ReplaceReviewDTO;



@Service
public class ReplaceReviewServiceImpl implements ReplaceReviewService{

	@Autowired
	ReplaceReviewDAO rprDAO;

	@Override
	public void register(ReplaceReviewDTO ad) {
		// TODO Auto-generated method stub
		rprDAO.insertSelectKey(ad);
	}

	@Override
	public ReplaceReviewDTO get(Long pno) {
		// TODO Auto-generated method stub
		return rprDAO.read(pno);
	}

	@Override
	public boolean modify(ReplaceReviewDTO ad) {
		// TODO Auto-generated method stub
		return rprDAO.update(ad) == 1;//수정, 삭제가 이루어지면 1이라는 값이 반환되서 true/false처리가능
	}

	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return rprDAO.delete(pno) == 1;
	}

	@Override
	public List<ReplaceReviewDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return rprDAO.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return rprDAO.getTotalCount(cri);
	}


}
