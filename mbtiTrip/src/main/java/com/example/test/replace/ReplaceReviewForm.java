package com.example.test.replace;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceReviewForm {

	@NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200)
    private String title;

	@NotEmpty(message = "여행카테고리는 필수항목입니다.")
	private Integer adventureCategoryID; 

 	
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
