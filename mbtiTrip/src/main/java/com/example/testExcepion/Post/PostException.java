package com.example.testExcepion.Post;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.test.POST.DAO.PostDAO;
import com.example.test.POST.DTO.PostDTO;

import lombok.Getter;

@Getter
public class PostException extends RuntimeException{

	private final PostExceptionEnum postExceptionEnum;
	
	
	public PostException(PostExceptionEnum postExceptionEnum) {
		super(postExceptionEnum.getMessage());
		this.postExceptionEnum = postExceptionEnum;
	}
	


}

