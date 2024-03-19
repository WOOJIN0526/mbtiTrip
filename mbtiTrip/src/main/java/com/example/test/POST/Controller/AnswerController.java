package com.example.test.POST.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.Service.AnswerService;

@Controller
public class AnswerController {

	@Autowired
	AnswerService answerService;
	
	 @PostMapping
	    public ResponseEntity<String> createAnswer(@RequestBody AnswerDTO answerDTO) {
	        answerService.saveAnswer(answerDTO);
	        return new ResponseEntity<>("답변이 성공적으로 생성되었습니다", HttpStatus.CREATED);
	    }

	    @PutMapping("/{answerId}")
	    public ResponseEntity<String> updateAnswer(@PathVariable Integer answerId, @RequestBody AnswerDTO answerDTO) {
	        answerDTO.setAnswerID(answerId);
	        answerService.updateAnswer(answerDTO);
	        return new ResponseEntity<>("답변이 성공적으로 업데이트되었습니다", HttpStatus.OK);
	    }

	    @DeleteMapping("/{answerId}")
	    public ResponseEntity<String> deleteAnswer(@PathVariable Integer answerId) {
	        answerService.deleteAnswer(answerId);
	        return new ResponseEntity<>("답변이 성공적으로 삭제되었습니다", HttpStatus.OK);
	    }

	    @GetMapping("/{answerId}")
	    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable Integer answerId) {
	        AnswerDTO answerDTO = answerService.getAnswerById(answerId);
	        return new ResponseEntity<>(answerDTO, HttpStatus.OK);
	    }

	    @GetMapping
	    public ResponseEntity<List<AnswerDTO>> getAllAnswers() {
	        List<AnswerDTO> answerDTOList = answerService.getAllAnswers();
	        return new ResponseEntity<>(answerDTOList, HttpStatus.OK);
	    }
}
