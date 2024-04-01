package com.example.test.replace.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.test.paging.Criteria;
import com.example.test.replace.DTO.ReplaceDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

public interface ReplaceReviewService {

public void register(ReplaceReviewDTO ad);
	
	public ReplaceReviewDTO get(Long pno);
	
	public boolean modify(ReplaceReviewDTO ad);
	
	public boolean remove(Long pno);
	
	public List<ReplaceReviewDTO> getList(Criteria cri);
	
	//adventureDAO의 gettotalcnt 호출용
	public int getTotal(Criteria cri);
}
