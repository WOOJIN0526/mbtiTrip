package com.example.test.POST.Service;



import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DAO.AnswerDAO;
import com.example.test.POST.DTO.Answer;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.User.DTO.UserDTO;

@Service
public  class AnswerServiceImpi implements AnswerService {

	@Autowired
	AnswerDAO answerDAO;
	
	@Override
	public AnswerDTO create(PostDTO postDto, String content, UserDTO author) {
		 AnswerDTO answerDto = new AnswerDTO();
	        answerDto.setContent(content);
	        answerDto.setUpdateDate(LocalDateTime.now());
	        answerDto.setPost(postDto);
	        answerDto.setAuthor(author);
	        
	        return this.answerDAO.save(answerDto);
	}

	@Override
	public AnswerDTO getAnswer(Integer answerid) {
		Optional<AnswerDTO> answer = this.answerDAO.findById(answerid);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	@Override
	public AnswerDTO modify(AnswerDTO answerDto, String content) {
		// TODO Auto-generated method stub
				answerDto.setContent(content);
				answerDto.setModifiDate(LocalDateTime.now());
				return this.answerDAO.save(answerDto);
	}

	@Override
	public void delete(AnswerDTO answerDto) {
		 this.answerDAO.delete(answerDto);
		
	}

	@Override
	public AnswerDTO vote(AnswerDTO answerDto, UserDTO siteUserDto) {
		 	answerDto.getVoter().add(siteUserDto);
	        
	        return this.answerDAO.save(answerDto);
	}

}
