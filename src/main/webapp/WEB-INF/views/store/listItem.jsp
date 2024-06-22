<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<style>
	ul li {
		display: inline-block;
	}
</style>
<div style="width:1000px;margin:70px auto 0px auto;">
 <h2 style="text-align:center;">상품등록 리스트</h2>

<table border="1">
	<tr>
		<th style="width:100px;">브랜드명</th>
		<th style="width:600px;">상품명</th>
		<th style="width:100px;">가격</th>
		<th style="width:100px;">재고</th>
		<th style="width:50px;">조회수</th>
		<th style="width:49px"></th>	
	</tr>
	<c:forEach items="${item }" var="it">
	<tr>
		<td>${it.pr_brand }</td>
		<td>${it.pr_title }</td>
		<td>${it.pr_price }</td>
		<td>${it.pr_cnt }</td>
		<td>${it.pr_viewcnt }</td>
		<td><a href="/seller/delete?pr_num=${it.pr_num}">삭제</a></td>
	</tr>
</c:forEach>	
</table>
<ul style="list-style:none; text-align:center;">
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="listItem?page=${pageMaker.startPage - 1 }"><</a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="listItem?page=${idx }">${idx }</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="listItem?page=${pageMaker.endPage + 1 }">></a>
	</c:if>
</ul>	
	<div style="text-align:right;">
		<button onclick="location.href='/seller/registItem'">상품등록</button>
	</div>
</div>