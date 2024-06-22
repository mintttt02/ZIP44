<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<style>
input {
	width: 100%;
}
</style>
<!-- 작성자 : 조현호 -->
<div style="width: 500px; margin: 70px auto 0 auto;">
	
	<h2 style="text-align: center;">회원 상세정보</h2>
	<table border="1">
		<tr>
			<td style="width: 199px;">이메일</td>
			<td style="width: 300px;"><input type="text" name="USER_EMAIL"
				value="${detail.USER_EMAIL }" readonly></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="USER_NAME"
				value="${detail.USER_NAME }" readonly></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><input type="text" name="USER_NICKNAME"
				value="${detail.USER_NICKNAME }" readonly></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="USER_PHONE"
				value="${detail.USER_PHONE }" readonly></td>
		</tr>
		<tr>
			<td>웹사이트</td>
			<td><input type="text" name="USER_SITE"
				value="${detail.USER_SITE }" readonly></td>
		</tr>
		<tr>
			<td>메시지</td>
			<td><input type="text" name="USER_MSG"
				value="${detail.USER_MSG }" readonly></td>
		</tr>
		<tr>
			<td>차단여부</td>
			<td><input type="text" name="USER_DENIED"
				value="${detail.USER_DENIED }" readonly></td>
		</tr>
		<tr>
			<td>회원가입 날짜</td>
			<td><input type="text" name="USER_JOIN_DATE"
				value="${detail.USER_JOIN_DATE }" readonly></td>
		</tr>
		<tr>
			<td>회원등급</td>
			<td><input type="text" name="USER_TYPE"
				value="${detail.USER_TYPE }" readonly></td>
		</tr>

	</table>
	<a href="/admin/update?USER_EMAIL=${detail.USER_EMAIL}">수정</a>
	<a href="/admin/list"
		style="float: right; color: #000000; text-decoration: none;"><h3><<
			돌아가기</h3></a>
</div>