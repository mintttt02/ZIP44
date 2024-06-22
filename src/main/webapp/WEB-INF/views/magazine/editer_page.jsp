<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- 작성자 : 최나현 -->
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<c:url value="/resources/css/editer_page.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<div class="editer_page">
	<div class="editer_title">에디터 페이지</div>
	<div class="editer_list">
		<div class="editer_list_item" onclick="location.href='/magazine/magazine_form'">
			<div class="editer_menu">매거진 글쓰기</div>
			<div class="arrow">></div>	
		</div>
		<div class="editer_underline"></div>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>
</body>
</html>