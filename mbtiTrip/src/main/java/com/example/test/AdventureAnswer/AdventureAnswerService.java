package com.example.test.AdventureAnswer;

import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.User.DTO.UserDTO;

public interface AdventureAnswerService {

	 public AdventureAnswerDTO create(AdventureDTO adventureDto, String content, UserDTO writer);

	 public AdventureAnswerDTO getAnswer(Integer adventureID);
	 
	 public AdventureAnswerDTO modify(AdventureAnswerDTO adventureAnswerDto, String content);
	 
	 public void delete(AdventureAnswerDTO adventureAnswerDto);
}
