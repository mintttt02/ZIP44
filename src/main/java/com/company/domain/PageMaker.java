package com.company.domain;

public class PageMaker {
	
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {	// 데이터의 총 갯수
		this.totalCount = totalCount;
		
		calcData();
	}
	
	private void calcData() {
		
		endPage = (int)(Math.ceil(cri.getPage() / (double)displayPageNum) * displayPageNum);
		// endPage = 현재 페이지 번호 / 페이지 번호의 수
		startPage = (endPage - displayPageNum) + 1;
		// startPage = (페이지 번호의 수) + 1
		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
		
		if(endPage > tempEndPage) {
			endPage = tempEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		// prev의 경우 startPage가 1이 아닌지를 검사하는 것으로 충분함.
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
		// next의 경우 뒤에 데이터가 더 남아 있는지에 대한 처리이므로, endPage * perPageNum이 totalCount보다 작은지 확인
		// ex) perPageNum이 10이고, endPage가 10인 상황에서 totalCount가 101이라면 next는 true가 되어야 함
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public Criteria getCri() {
		return cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage=" + startPage + ", endPage=" + endPage + ", prev="
				+ prev + ", next=" + next + ", displayPageNum=" + displayPageNum + ", cri=" + cri + "]";
	}
	
	
}
