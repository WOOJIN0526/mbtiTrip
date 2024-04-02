package com.example.test.User.DTO;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.context.properties.bind.Name;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QnADTO {

	
	private Integer QID;
	
	private String title;
	 
	private String contents;
	
	private String userName;

	private LocalDateTime updateDate;
		
	private List<QAnswerDTO> answer;
	
	
	@Override
    public String toString() {
        return "YourClass{" +
                "QID=" + QID +
                ", QName='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", userName='" + userName + '\'' +
                ", answer=" + answer +
                '}';
    }
}
