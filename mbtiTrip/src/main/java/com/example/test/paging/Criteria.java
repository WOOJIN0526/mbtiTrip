package com.example.test.paging;





import com.example.test.POST.DTO.PostDTO;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString    //JDOC
public class Criteria {//검색기준
	
	private PostDTO postID;
	private int pageNum;
	private int amount;
	private String type;
	private String keyword;
	
	//기본 페이지를 1페이지에 10개씩 보여준다
	public Criteria() {
		this(1,10);
	}

	//매개변수로 들어오는 값을 이용하여 조정할수 있다.
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	//UriComponentsBuilder를 이용하여 링크 생성
//	public String getListLink() {
//		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
//				.queryParam("pageNum", pageNum)
//				.queryParam("amount", amount);
//		
//		return builder.toUriString();
//		
//	}
	
	public String[] getTypeArr() {
		return type == null ? new String[]{} : type.split("");
	}
	
	    
}
