package com.example.test.POST.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {

	@NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 100)
    private String title;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    
    @NotEmpty(message = "MBTI는 필수항목입니다")
    private String mbti;
}
