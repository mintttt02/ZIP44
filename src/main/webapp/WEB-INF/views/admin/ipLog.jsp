<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<style>
	td:not(:first-child) {
		text-align:center;
	}
	ul li {
		display:inline-block;
	}
	ul li a {
		text-decoration:none;
	}
</style>
 <div style="width:1000px;margin:70px auto 0px auto;">
 <h2 style="text-align:center;">회원정보</h2>

<table border="1" style="margin:0 auto;">
	<tr>
		<th style="width:200px;">IP 주소</th>
		<th style="width:300px;">접속날짜</th>
		<th style="width:200px;">로그인 이메일</th>
	</tr>
<c:forEach items="${ip}" var="ip">	
	<tr>
		<td>${ip.ip_addr}</td>
		<td>${ip.ip_date}</td>
		<td>${ip.login_email}</td>
	</tr>
</c:forEach>	
</table>
<ul style="list-style:none; text-align:center;">
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="iplog?page=${pageMaker.startPage - 1 }"><</a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="iplog?page=${idx }">${idx }</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="iplog?page=${pageMaker.endPage + 1 }">></a>
	</c:if>
</ul>	
<a href="/admin/admin" style="float:right;color:#000000;text-decoration:none;"><h3><< 돌아가기</h3></a>

</div>
