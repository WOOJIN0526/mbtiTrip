package com.example.test.Adventure.Service;

import java.util.List;


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.paging.Criteria;

public interface Adventure_ReviewService {

	public void register(Adventure_ReviewDTO adr);
	
	public Adventure_ReviewDTO get(Long pno);
	
	public boolean modify(Adventure_ReviewDTO adr);
	
	public boolean remove(Long pno);
	
	public List<Adventure_ReviewDTO> getList(Criteria cri);
	
	//adventureDAO의 gettotalcnt 호출용
	public int getTotal(Criteria cri);
	 
	
}
