package com.example.test.POST.Service;





import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerPageDTO;
import com.example.test.paging.Criteria;

import jakarta.transaction.Transactional;

@Service
public  class AnswerServiceImpi implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
	
	@Autowired
	PostDAO postDAO;

	@Transactional
	@Override
	public int register(AnswerDTO answer) {
		
		postDAO.updateAnswerCnt(answer.getPno(), 1);
		
		return answerDAO.insert(answer);
	}

	@Override
	public AnswerDTO get(Long ano) {
		// TODO Auto-generated method stub
		return answerDAO.read(ano);
	}

	@Override
	public int modify(AnswerDTO answer) {
		// TODO Auto-generated method stub
		return answerDAO.update(answer);
	}

	@Transactional
	@Override
	public int remove(Long ano) {
		AnswerDTO anwer = answerDAO.read(ano);
		
		postDAO.updateAnswerCnt(anwer.getPno(), -1);
		return answerDAO.delete(ano);
	}

	@Override
	public List<AnswerDTO> getList(Criteria cri, Long pno) {
		// TODO Auto-generated method stub
		return answerDAO.getListWithPaging(cri, pno);
	}

	@Override
	public AnswerPageDTO getListPage(Criteria cri, Long pno) {
		// TODO Auto-generated method stub
		return new AnswerPageDTO(
				answerDAO.getCountByPno(pno),
				answerDAO.getListWithPaging(cri, pno));
	}
	
	
	
}
