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

<h2>스토리 댓글 신고 리스트 </h2>

<table border=1 class="reportTable">
	<tr>
		<th>스토리번호</th>
		<th>스토리댓글번호</th>
		<th>신고횟수</th>
		<th>삭제</th>
		<th>신고취소</th>
		<th>자세히</th>
	</tr>
	<c:forEach items="${list }" var="sc" varStatus="status">	
	<tr>
		<td>${storyNum[status.index].st_num}</td>
		<td>${sc.st_com_num}</td>
		<td>${sc.reportCnt}</td>
		<td><a href='/admin/reportDel?column=3&&number=${sc.st_com_num}'>삭제</a></td>
		<td><a href='/admin/reportCancel?column=3&&cancel_num=${sc.st_com_num}'>신고취소</a></td>
		<td><a href="/admin/storyReportDetail?st_num=${storyNum[status.index].st_num}" 
				target="_blank">자세히</a></td>
	</tr>
	</c:forEach>	
</table>
<ul >
	<!-- ${pageMaker.prev }를 이용해서 이전페이지로 가는 링크가 필요한지 판단  -->
	<c:if test="${pageMaker.prev }">	
		<li>
			<a href="/admin/ReportList?page=${pageMaker.startPage - 1 }&&column=3"></a>
		</li>
	</c:if>
	<!-- 각 페이지의 번호 출력 -->
	<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }" var="idx">
		<li
			<c:out value="${pageMaker.cri.page == idx?'class=active':'' }"/>>
			<a href="/admin/ReportList?page=${idx }&&column=3">${idx }</a>
		</li>
	</c:forEach>

	<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
		<li><a href="/admin/ReportList?page=${pageMaker.endPage + 1 }&&column=3"></a></li>
	</c:if>
</ul>
<a href="/admin/admin" ><h3><< 돌아가기</h3></a>
</div>



</body>
</html>