package com.example.test.AdventureReviewAnswer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.Adventure.DTO.Adventure_ReviewDTO;

import com.example.test.User.DTO.UserDTO;

@Service
public class AdventureReviewAnswerServiceImpl implements AdventureReviewAnswerService{

	@Autowired
	AdventureReviewAnswerDAO adraDao;
	
	@Override
	public AdventureReviewAnswerDTO create(Adventure_ReviewDTO adventureReviewDto, String content, UserDTO writer) {
		AdventureReviewAnswerDTO answerDto = new AdventureReviewAnswerDTO();
        answerDto.setContent(content);
        answerDto.setUpdateDate(LocalDateTime.now());
        answerDto.setAdventureReview(adventureReviewDto);;;
        answerDto.setWriter(writer);
        
        return this.adraDao.save(answerDto);
	}

	@Override
	public AdventureReviewAnswerDTO getAnswer(Integer answerID) {
		Optional<AdventureReviewAnswerDTO> answer = this.adraDao.findById(answerID);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	@Override
	public AdventureReviewAnswerDTO modify(AdventureReviewAnswerDTO adventureAnswerDto, String content) {
		adventureAnswerDto.setContent(content);
		adventureAnswerDto.setModifyDate(LocalDateTime.now());
		return this.adraDao.save(adventureAnswerDto);
	}

	@Override
	public void delete(AdventureReviewAnswerDTO adventureAnswerDto) {
		
		this.adraDao.delete(adventureAnswerDto);
	}

	
}
