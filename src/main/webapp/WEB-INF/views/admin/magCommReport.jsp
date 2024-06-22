<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- 작성자 : 최나현 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/report.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>

<div class="all_report">

<div class="report_category">
	<div><a href = "/admin/ReportList?column=1">매거진 댓글 신고 리스트 ></a></div>
	<div><a href = "/admin/ReportList?column=2">스토리 신고 리스트 ></a></div>
	<div><a href = "/admin/ReportList?column=3">스토리 댓글 신고 리스트 ></a></div>
	<div><a href = "/admin/ReportList?column=4">리뷰 신고 리스트 ></a></div>

</div>

<h2>매거진 댓글 신고 리스트 </h2>

<table border=1 class="reportTable">
	<tr>
		<th>매거진제목</th>
		<th>댓글내용</th>
		<th>댓글번호</th>
		<th>신고횟수</th>
		<th>삭제</th>
		<th>신고취소</th>
	</tr>
	

	<c:forEach var="rp" items="${list }" varStatus="status"  >	
	<tr>
			<td><a href='/magazine/magazine_detail?mag_num=${magtitle[status.index].mag_num}' target="_blank">
				<span>${magtitle[status.index].mag_title }</span></a></td>
			<td><span>${magtitle[status.index].mag_com_content }</span></td>
			<td>${rp.mag_com_num}</td>
			<td>${rp.reportCnt}</td>
			<td><a href='/admin/reportDel?column=1&&number=${rp.mag_com_num}'>삭제</a></td>
			<td><a href='/admin/reportCancel?column=1&&cancel_num=${rp.mag_com_num }'>신고취소</a></td>
	</tr>
	</c:forEach>
</table>

<ul >
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="/admin/ReportList?page=${pageMaker.startPage - 1 }&&column=1"></a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="/admin/ReportList?page=${idx }&&column=1">${idx }</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="/admin/ReportList?page=${pageMaker.endPage + 1 }&&column=1"></a></li>
	</c:if>
</ul>
<a href="/admin/admin" ><h3><< 돌아가기</h3></a>
</div>


</body>
</html>