package com.example.test.POST.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
	
		private int page;                //현재 페이지 번호
	    private int perPageNum;          //한 페이지 당 게시물 수
	    private String keyword, option;  //검색 시 페이징 처리 유지를 위한 검색조건들

	    public String getKeyword() {
	        return keyword;
	    }
	    public void setKeyword(String keyword) {
	        this.keyword = keyword;
	    }
	    public String getOption() {
	        return option;
	    }
	    public void setOption(String option) {
	        this.option = option;
	    }

	//현재 페이지 게시글 시작 번호
	public int getPageStart() {
	    return (this.page-1)*perPageNum;
	    }
	    
	    //첫 접근 시 현재 페이지는 1, 게시물 개수 10개
	    public Criteria() {
	        this.page = 1;
	        this.perPageNum = 10;
	    }
	    
	    public int getPage() {
	        return page;
	    }
	    
	    //음수가 될 경우는 시작 페이지를 리턴
	    public void setPage(int page) {
	        if(page <= 0) {
	            this.page = 1;
	        } else {
	            this.page = page;
	        }
	    }
	    
	    public int getPerPageNum() {
	        return perPageNum;
	    }
	    
	    //페이지 당 게시글 수가 변하지 않아야 한다
	    public void setPerPageNum(int pageCount) {
	        int cnt = this.perPageNum;
	        if(pageCount != cnt) {
	            this.perPageNum = cnt;
	        } else {
	            this.perPageNum = pageCount;
	        }
	    }
	    
	    @Override
	    public String toString() {
	    	return "Criteria[page = " + page + ", perPageNum = " + perPageNum + "]";
	    }
	    
	}