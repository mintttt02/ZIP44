package com.company.domain;

/*
 * Criteria : '검색 기준, 분류기준'(사전적 의미)
 * 기본값으로 페이지 번호는 1페이지로 지정하고 리스트당 데이터의 수는 10으로 지정함
 */
public class Criteria {
	
	private int page;
	private int perPageNum;
	private String bi_category;	// mapper의 카테고리 값을 넘겨주기 위함
	private String report_category; //신고 카테고리 이동을 하기위해 사용
	
	
	public String getReport_category() {
		return report_category;
	}

	public void setReport_category(String report_category) {
		this.report_category = report_category;
	}

	public String getBi_category() {
		return bi_category;
	}

	public void setBi_category(String bi_category) {
		this.bi_category = bi_category;
	}

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	
	public void setPage(int page) {
		if (page <=0) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	
	public void setPerPageNum(int perPageNum) {
		if(perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}
	
	// XML Mapper에서 사용하는 getter
	// limit 구문에서 시작 위치를 지정할 때 사용
	// 시작 데이터 번호 = (페이지 번호 - 1) * 페이지 당 보여지는 개수
	public int getPageStart() {		
		return (this.page - 1) * perPageNum;
	}
	
	public int getPerPageNum() {
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
