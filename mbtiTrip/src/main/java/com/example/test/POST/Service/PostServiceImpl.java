package com.example.test.POST.Service;



import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.PostDAO;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;






@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	

	@Override
	public void register(PostDTO post) {
		// TODO Auto-generated method stub
		postDAO.insertSelectKey(post);  
	}



	@Override
	public PostDTO get(Long pno) {
		// TODO Auto-generated method stub
		return postDAO.read(pno);
	}



	@Override
	public boolean modify(PostDTO post) {
		// TODO Auto-generated method stub
		return postDAO.update(post) == 1;//수정, 삭제가 이루어지면 1이라는 값이 반환되서 true/false처리가능
	}



	@Override
	public boolean remove(Long pno) {
		// TODO Auto-generated method stub
		return postDAO.delete(pno) == 1;
	}



	@Override
	public List<PostDTO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		return postDAO.getListWithPaging(cri);
	}



	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return postDAO.getTotalCount(cri);
	}

	





	
	
    

}
