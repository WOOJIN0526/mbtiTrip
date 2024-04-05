package com.example.test.ReplaceReviewAnswer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceReviewDTO;

@Service
public class ReplaceReviewAnswerServiceImpl implements ReplaceReviewAnswerService {

	@Autowired
	ReplaceReviewAnswerDAO rpraDao;

	@Override
	public ReplaceReviewAnswerDTO create(ReplaceReviewDTO replaceReviewDto, String content, UserDTO writer) {
		ReplaceReviewAnswerDTO answerDto = new ReplaceReviewAnswerDTO();
        answerDto.setContent(content);
        answerDto.setUpdateDate(LocalDateTime.now());
        answerDto.setReplaceReview(replaceReviewDto);;
        answerDto.setWriter(writer);
        
        return this.rpraDao.save(answerDto);
	}

	@Override
	public ReplaceReviewAnswerDTO getAnswer(Integer answerID) {
		Optional<ReplaceReviewAnswerDTO> answer = this.rpraDao.findById(answerID);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	@Override
	public ReplaceReviewAnswerDTO modify(ReplaceReviewAnswerDTO rpraDto, String content) {
		rpraDto.setContent(content);
		rpraDto.setModifyDate(LocalDateTime.now());
		return this.rpraDao.save(rpraDto);
	}

	@Override
	public void delete(ReplaceReviewAnswerDTO rpraDto) {
		// TODO Auto-generated method stub
		this.rpraDao.delete(rpraDto);
	}
	
	
	
}
