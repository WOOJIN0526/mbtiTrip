package com.example.test.ReplaceAnswer;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.User.DTO.UserDTO;
import com.example.test.replace.DTO.ReplaceDTO;

@Service
public class ReplaceAnswerServiceImpl implements ReplaceAnswerService {

	@Autowired
	ReplaceAnswerDAO rpaDao;

	@Override
	public ReplaceAnswerDTO create(ReplaceDTO replaceDto, String content, UserDTO writer) {
		ReplaceAnswerDTO answerDto = new ReplaceAnswerDTO();
        answerDto.setContent(content);
        answerDto.setUpdateDate(LocalDateTime.now());
        answerDto.setReplace(replaceDto);;
        answerDto.setWriter(writer);
        
        return this.rpaDao.save(answerDto);
	}

	@Override
	public ReplaceAnswerDTO getAnswer(Integer answerID) {
		Optional<ReplaceAnswerDTO> answer = this.rpaDao.findById(answerID);
		if(answer.isPresent()) {
			return answer.get();
		}
		return null;
	}

	@Override
	public ReplaceAnswerDTO modify(ReplaceAnswerDTO rpaDto, String content) {
		rpaDto.setContent(content);
		rpaDto.setModifyDate(LocalDateTime.now());
		return this.rpaDao.save(rpaDto);
	}

	@Override
	public void delete(ReplaceAnswerDTO rpaDto) {
		// TODO Auto-generated method stub
		this.rpaDao.delete(rpaDto);
	}
	
	
}
