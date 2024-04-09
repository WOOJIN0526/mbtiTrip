package com.example.test.paging;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageDTO { //JDOC

	private int pageCount; //전체페이지 수
	private int startPage; // 시작페이지
	private int endPage; // 끝페이지
	private int realEnd; // 실제존재하는 페이지수
	private boolean prev, next; // 이전, 다음
	private int total; //전체 데이터수
	private Criteria criteria;
	
	public PageDTO() {
		
	}
	
	public PageDTO(int total, int pageCount, Criteria criteria) {
		this.total = total;
		this.criteria = criteria;
		this.pageCount = pageCount;
		
		this.endPage= (int)(Math.ceil(criteria.getPageNum()*1.0/pageCount))*pageCount;
		this.startPage = endPage - (pageCount -1);
		
		realEnd = (int)(Math.ceil(total*1.0 / criteria.getAmount()));
		
		if(endPage > realEnd) {
			endPage = realEnd ==0 ? 1 : realEnd; 
		}
		
		prev = startPage > 1;
		next = endPage < realEnd;
	}
}
