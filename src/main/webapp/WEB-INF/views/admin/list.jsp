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
<script>
	var result = '${msg}';
	
	if(result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>
<!-- 작성자 : 조현호 -->

 <div style="width:1000px;margin:70px auto 0px auto;">
 <h2 style="text-align:center;">회원정보</h2>

<table border="1">
	<tr>
		<th style="width:400px;">USER_EMAIL</th>
		<th style="width:300px;">USER_NICKNAME</th>
		<th style="width:100px;">USER_DENIED</th>
		<th style="width:100px;">USER_TYPE</th>
		<th style="width:99px;"></th>	
	</tr>
<c:forEach items="${list }" var="vo">	
	<tr>
		<td><a href='/admin/detail?USER_EMAIL=${vo.USER_EMAIL }'>${vo.USER_EMAIL }</a></td>
		<td>${vo.USER_NICKNAME }</td>
		<td>${vo.USER_DENIED }</td>
		<td>${vo.USER_TYPE }</td>
		<td>
		<c:if test="${vo.USER_TYPE ne 0 && vo.USER_TYPE ne 3}">
		<a href='/admin/delete?USER_EMAIL=${vo.USER_EMAIL }'>삭제</a>
		</c:if>
		</td>
	</tr>
</c:forEach>	
</table>
<ul style="list-style:none; text-align:center;">
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="list?page=${pageMaker.startPage - 1 }"><</a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="list?page=${idx }">${idx }</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="list?page=${pageMaker.endPage + 1 }">></a>
	</c:if>
</ul>	
<a href="/admin/admin" style="float:right;color:#000000;text-decoration:none;"><h3><< 돌아가기</h3></a>

</div>