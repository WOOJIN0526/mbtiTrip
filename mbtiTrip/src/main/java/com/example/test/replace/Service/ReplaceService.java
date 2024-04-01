package com.example.test.replace.Service;

import java.util.List;


import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceDTO;

public interface ReplaceService {

	public void register(ReplaceDTO ad);
	
	public ReplaceDTO get(Long pno);
	
	public boolean modify(ReplaceDTO ad);
	
	public boolean remove(Long pno);
	
	public List<ReplaceDTO> getList(Criteria cri);
	
	//adventureDAO의 gettotalcnt 호출용
	public int getTotal(Criteria cri);
}
