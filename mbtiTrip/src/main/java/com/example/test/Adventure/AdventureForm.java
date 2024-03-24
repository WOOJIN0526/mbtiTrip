package com.example.test.Adventure;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdventureForm {

	@NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 100)
    private String title;

    @NotEmpty(message = "MBTI는 필수항목입니다")
    private String mbti;
    
    @NotEmpty(message = "카테고리는 필수항목입니다")
    private String categoryName;
    
    @NotEmpty(message = "주소는 필수입니다")
    private String AdventureLocation;
    
    @NotEmpty(message = "가격은 필수입니다")
    private String AdventurePrice;
    
    @NotEmpty(message = "연락처는 필수입니다")
    private String Tel;
    
    @NotEmpty(message = "관리자성함은 필수입니다")
    private String Admin;
    
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
    
}
