package com.example.test.POST.Service;



import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.item.DTO.ItemDTO;

public interface AnswerService {

	 public AnswerDTO create(PostDTO questionDto, String content, UserDTO writer);

	 public AnswerDTO getAnswer(Integer answerID);
	 
	 public AnswerDTO modify(AnswerDTO answerDto, String content);
	 
	 public void delete(AnswerDTO answerDto);

	public AnswerDTO create(PostReviewDTO postDto, String content, UserDTO userDto);

	public AnswerDTO create(ItemDTO postDto, String content, UserDTO userDto);
	 
	
	
}
