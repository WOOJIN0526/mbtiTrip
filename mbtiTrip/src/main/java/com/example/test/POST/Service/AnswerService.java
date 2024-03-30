package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerPageDTO;
import com.example.test.paging.Criteria;

public interface AnswerService {

	public int register(AnswerDTO answer);
	
	public AnswerDTO get(Long ano);
	
	public int modify(AnswerDTO answer);
	
	public int remove(Long ano);
	
	public List<AnswerDTO> getList(Criteria cri, Long pno);
	
	public AnswerPageDTO getListPage(Criteria cri, Long pno);
	
}
