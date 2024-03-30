package com.example.test.paging;

import groovy.transform.ToString;
import lombok.Getter;

@Getter
@ToString
public class PageDTO {

	//페이징 처리시 사용
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		//페이징의 끝번호 계산
		this.endPage = (int) (Math.ceil(cri.getPageNum()/10.0))*10;
		
		//페이징의 시작번호 계산
		this.startPage = this.endPage - 9;
		
		//total(전체 데이터 수)을 통한 endpage 재계산
		int realEnd = (int) (Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		//이전링크 계산
		this.prev = this.startPage > 1;
		
		//다음링크 계산
		this.next = this.endPage < realEnd;
	}
}
