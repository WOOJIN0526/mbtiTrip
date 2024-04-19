package com.example.test.paging;

import lombok.Data;

@Data
public class Page {
	
	private int pageNum;			// 현재 페이지 번호
	private int rowsPerPage;		// 페이지 당 게시글 수
	
	private int startPage;			// 시작 페이지 번호
	private int endPage;			// 끝 페이지 번호
	
	private int firstPage;			// 첫 번째 페이지 번호
	private int lastPage;			// 마지막 페이지 번호
	
	private int prev;				// 이전 페이지 번호
	private int next;				// 다음 페이지 번호
	private int pageCount = 5;		// 한 번에 보여질 페이지 최대 개수
	private int totalCount;			// 전체 데이터 개수
	
	private int startRowIndex;		// 시작 행 index
	
	private String keyword;			// 검색어
	private int PostCateGoryID;			// 검색어
	
	public Page() {
		this(0, 10);
	}
	
	public Page(int pageNum, int rowsPerPage) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
		
		this.startPage =  ( (pageNum-1) / pageCount ) * pageCount + 1;
		this.endPage = ( ((pageNum-1) / rowsPerPage ) + 1 )  * pageCount;
	}
	

	// 현재페이지 변호, 페이지 당 게시글 수, 전체 데이터 개수, 노출 페이지 개수
	public Page(int pageNum, int rowsPerPage, int totalCount, int pageCount) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
		this.totalCount = totalCount;
		this.pageCount = pageCount;
		
		this.prev = pageNum - 1;
		this.next = pageNum + 1;
		
		this.startRowIndex = (pageNum - 1) * rowsPerPage;
		
		this.firstPage = 1;
		
		if( totalCount % rowsPerPage == 0 )
			this.lastPage = totalCount / rowsPerPage;
		else
			this.lastPage = (totalCount / rowsPerPage) + 1;
		
		this.startPage =  ( (pageNum-1) / pageCount ) * pageCount + 1;
		
		this.endPage = ( ((pageNum-1) / pageCount ) + 1 )  * pageCount;
		
		if( this.endPage > lastPage )
			this.endPage = lastPage;
		
	}
	
	
	
}