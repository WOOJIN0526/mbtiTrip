package com.example.test.AdventureAnswer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserDTO;

@Service
public class AdventureAnswerServiceImpl implements AdventureAnswerService {

	@Autowired
	AdventureAnswerDAO adaDao;
	
	@Override
	public AdventureAnswerDTO create(AdventureDTO adventureDto, String content, UserDTO writer) {
		AdventureAnswerDTO answerDto = new AdventureAnswerDTO();
        answerDto.setContent(content);
        answerDto.setUpdateDate(LocalDateTime.now());
        answerDto.setAdventure(adventureDto);;
        answerDto.setWriter(writer);
        
        return this.adaDao.save(answerDto);
	}

	@Override
	public AdventureAnswerDTO getAnswer(Integer adventureID) {
		Optional<AdventureAnswerDTO> answer = this.adaDao.findById(adventureID);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	@Override
	public AdventureAnswerDTO modify(AdventureAnswerDTO adventureAnswerDto, String content) {
		adventureAnswerDto.setContent(content);
		adventureAnswerDto.setModifyDate(LocalDateTime.now());
		return this.adaDao.save(adventureAnswerDto);
	}

	@Override
	public void delete(AdventureAnswerDTO adventureAnswerDto) {
		// TODO Auto-generated method stub
		this.adaDao.delete(adventureAnswerDto);
	}

}
