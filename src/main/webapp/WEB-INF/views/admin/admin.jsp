<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>

<link rel="stylesheet" href="<c:url value="/resources/css/admin.css"/>">
<!-- 작성자 : 조현호 -->

<div class="admin_wrapper">
	<div class="admin_title">관리자 페이지</div>
	<div class="admin_list">
		<div class="admin_list_item" onclick="location.href='/admin/list'">
			<div class="admin_menu">회원리스트</div>
			<div class="arrow">></div>	
		</div>
		<div class="admin_underline"></div>
	</div>
	<div class="admin_list">
		<div class="admin_list_item" onclick="location.href='/admin/ReportList?column=1'">
			<div class="admin_menu">신고리스트</div>
			<div class="arrow">></div>	
		</div>
		<div class="admin_underline"></div>
	</div>
	<div class="admin_list">
		<div class="admin_list_item" onclick="location.href='/admin/list'">
			<div class="admin_menu">Q&A리스트</div>
			<div class="arrow">></div>	
		</div>
		<div class="admin_underline"></div>
	</div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp" %>