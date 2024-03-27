package com.example.test.User.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnADTO {

	
	private Integer QID;
	
	private String QC;
	
	private String contents;
	
	private String userName;

		
	private List<QAnswerDTO> answer;
	
	
	@Override
    public String toString() {
        return "YourClass{" +
                "QID=" + QID +
                ", QC='" + QC + '\'' +
                ", contents='" + contents + '\'' +
                ", userName='" + userName + '\'' +
                ", answer=" + answer +
                '}';
    }
}
