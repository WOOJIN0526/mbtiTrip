package com.example.test.Adventure.Service;

import java.util.List;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.paging.Criteria;

public interface AdventureService {

	public void register(AdventureDTO ad);
	
	public AdventureDTO get(Long pno);
	
	public boolean modify(AdventureDTO ad);
	
	public boolean remove(Long pno);
	
	public List<AdventureDTO> getList(Criteria cri);
	
	//adventureDAO의 gettotalcnt 호출용
	public int getTotal(Criteria cri);
}
