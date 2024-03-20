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

	private final ModelMapper modelMapper;
	
	private Answer of(AnswerDTO answerDto) {
	        return modelMapper.map(answerDto, Answer.class);
	    }
	    
	private AnswerDTO of(Answer answer) {
	        return modelMapper.map(answer, AnswerDTO.class);
	    }
	
	@Override
	public AnswerDTO create(PostDTO postDto, String content, UserDTO author) {
		 AnswerDTO answerDto = new AnswerDTO();
	        answerDto.setContent(content);
	        answerDto.setUpdateDate(LocalDateTime.now());
	        answerDto.setPost(postDto);
	        answerDto.setAuthor(author);
	        Answer answer = of(answerDto);
	        answer = this.answerDAO.save(answer);
	        answerDto.setId(answer.getPostID());
	        return answerDto;
	}

	@Override
	public AnswerDTO getAnswer(Integer id) {
		Optional<Answer> answer = this.answerDAO.findById(id);
        if (answer.isPresent()) {
            return of(answer.get());
        } else {
            throw new Exception("question not found");
        }
	}

	@Override
	public AnswerDTO modify(AnswerDTO answerDto, String content) {
		 	answerDto.setContent(content);
	        answerDto.setModifiDate(LocalDateTime.now());
	        this.answerDAO.save(of(answerDto));
	        return answerDto;
	}

	@Override
	public void delete(AnswerDTO answerDto) {
		 this.answerDAO.delete(of(answerDto));
		
	}

	@Override
	public AnswerDTO vote(AnswerDTO answerDto, UserDTO siteUserDto) {
		 answerDto.getVoter().add(siteUserDto);
	     this.answerDAO.save(of(answerDto));
	     return answerDto;
	}
	
	

    
    

}
