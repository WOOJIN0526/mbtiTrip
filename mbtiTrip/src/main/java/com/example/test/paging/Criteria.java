package com.example.test.paging;

import org.springframework.web.util.UriComponentsBuilder;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Criteria {//검색기준
	
	//페이징 처리를 위한
	private int pageNum;
	private int amount;
	
	//주제별 검색 처리를 위한
	private String type;
	private String keyword;
	
	//한 페이지에 얼만큼의 게시물을 허용할지(1페이지에 10개)
	public Criteria() {
		this(1,10);
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	// 검색조건이 각 글자별로 되어있으므로 검색 조건을 배열로 만들어서 한번에 처리
	public String[] getTypeArr() {
		return type == null? new String[] {}: type.split("");
	}
	
	public String getListLink() {
		//여러개의 파라미터들을 연결하여 url의 형태로 만들어주는 기능
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount",  this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		
		return builder.toUriString();
	}
}
