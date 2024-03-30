package com.example.test.POST.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class AnswerPageDTO {

	//댓글 목록과 전체댓글수를 전달 
	private int answerCnt;
	private List<AnswerDTO> list;
}
