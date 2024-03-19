package com.example.test.POST.Service;

import java.util.List;

import com.example.test.POST.DTO.AnswerDTO;

public interface AnswerService {

	void saveAnswer(AnswerDTO answerDTO);
    void updateAnswer(AnswerDTO answerDTO);
    void deleteAnswer(Integer answerId);
    AnswerDTO getAnswerById(Integer answerId);
    List<AnswerDTO> getAllAnswers();
}
