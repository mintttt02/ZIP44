<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<%@ include file="/WEB-INF/views/store/store_header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="/resources/css/more.css">
<script>
$("#more_selector").on("change", function() {
	$(this).find("option:eq(1)").prop("selected", "selected");
})
function sort(obj) {
	if(obj == "recent") {
		location.href='/store/more/${bi_category}?sort=recent';
	} else if (obj == "popular") {
		location.href='/store/more/${bi_category}?sort=popular';
	}
}
</script>
<div class="more_container">
	<div class="more_top">
		<div class="more_filter_section">
			<div class="more_filter_wrapper">
				<select class="more_selector" id="more_selector" onchange="sort(this.value);">
					<option value="pr_date" selected>최신순</option>
					<option value="popular">인기순</option>
					<option value="low_price">낮은 가격 순</option>
					<option value="high_price">높은 가격순</option>
					<option value="review_cnt">후기 순</option>
				</select>
			</div>
		</div>
	</div>

	<div class="more_item_group">
		<!-- 상품 목록 6개까지만 출력 -->
		<c:forEach items="${item }" var="it">
		<!-- 할인률 적용한 가격  -->
		<c:set var="price" value="${it.pr_price - (it.pr_price * (it.pr_sale * 0.01))}"></c:set>
		
		<div class="more_item">
			<a href="/store/view/${it.pr_num }">
				<div class="more_item_img">
					<img src="/zipImg/${it.pr_img}">
				</div>
			</a>
			<div class="more_item_description">
				<div class="more_item_name">[${it.pr_brand}] ${it.pr_title}</div>
				<div class="more_item_price">
					<s><fmf:formatNumber value="${it.pr_price }" type="number" /> 원</s>
				</div>
				<div class="more_price_dc">
					<span><fmf:formatNumber value="${price}" type="number" /></span>
					<span class="unit">원</span>
					<span>(${it.pr_sale }%)</span>
				</div>
			</div>
		</div>
		</c:forEach>
	</div>
	
	<c:if test="${pageMaker.startPage eq 1}">
	<div class="more_paging">
		<ul style="list-style:none; text-align:center;">
		<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
		<c:if test="${pageMaker.prev }">	
			<li>
				<a href="?page=${pageMaker.startPage - 1 }"></a>
			</li>
		</c:if>
		<!-- 각 페이지의 번호 출력 -->
		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
			<li
				<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
				<a href="?page=${idx }">${idx }</a>
			</li>
		</c:forEach>
		</ul>
		<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
			<li><a href="?page=${pageMaker.endPage + 1 }"></a>
		</c:if>	
	</div>
	</c:if>
</div>    

<%@ include file="/WEB-INF/views/footer.jsp" %>