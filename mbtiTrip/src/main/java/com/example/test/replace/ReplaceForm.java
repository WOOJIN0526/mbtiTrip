package com.example.test.replace;



import com.example.test.item.ItemType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceForm {

	@NotEmpty(message = "mbti는 필수항목입니다.")
 	private  Integer mbtiID;
	
 	@NotEmpty(message = "지역은 필수항목입니다.")
	private Integer cityID;
	
 	@NotEmpty(message = "연락처는 필수항목입니다.")
	private String tel; // 어드벤쳐 번호
 	
 	@NotEmpty(message = "여행카테고리는 필수항목입니다.")
	private Integer replaceCategoryID; // 게시판 카테고리(판매, 리뷰)?
 	
	@NotEmpty(message = "게시판카테고리는 필수항목입니다.")
	private Integer postCategoryID; // 게시판 카테고리(판매, 리뷰)?
	
 	@NotEmpty(message = "여행타입은 필수항목입니다.")
	private Integer replaceType; // 어드벤쳐 카테고리랑 타입이랑 무슨차이?
	
	@NotEmpty(message = "위치는 필수항목입니다.")
	private String replaceLocation; // 해당 어드벤쳐 위치
	
	@NotEmpty(message = "가격은 필수항목입니다.")
	private Integer replacePrice; // 어드벤쳐 가격
	
	@NotEmpty(message = "내용은 필수항목입니다.")
	@Size(max = 1000)
	private String replaceContents; // 어드벤쳐 내용
	
	@NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200)
    private String replaceName;
	
	@NotEmpty(message = "타입은 필수입니다")
	private ItemType Type;
	
	
	private String[] file;
	
}
