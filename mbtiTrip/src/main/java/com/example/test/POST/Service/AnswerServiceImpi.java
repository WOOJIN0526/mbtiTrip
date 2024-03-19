package com.example.test.POST.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DTO.AnswerDTO;

@Service
public  class AnswerServiceImpi implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
	
	 @Override
	    public void saveAnswer(AnswerDTO answerDTO) {
	        answerDAO.saveAnswer(answerDTO);
	    }

	    @Override
	    public void updateAnswer(AnswerDTO answerDTO) {
	        answerDAO.updateAnswer(answerDTO);
	    }

	    @Override
	    public void deleteAnswer(Integer answerId) {
	        answerDAO.deleteAnswer(answerId);
	    }

	    @Override
	    public AnswerDTO getAnswerById(Integer answerId) {
	        return answerDAO.getAnswerById(answerId);
	    }

	    @Override
	    public List<AnswerDTO> getAllAnswers() {
	        return answerDAO.getAllAnswers();
	    }


    
    

}
