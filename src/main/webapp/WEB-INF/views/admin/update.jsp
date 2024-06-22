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
	<form role="form" method="post">
	<h2 style="text-align: center;">회원 상세정보</h2>
	<table border="1">
		<tr>
			<td style="width: 199px;">이메일</td>
			<td style="width: 300px;"><input type="text" 
				value="${update.USER_EMAIL }" readonly></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="USER_NAME"
				value="${update.USER_NAME }" readonly></td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td><input type="text" name="USER_NICKNAME"
				value="${update.USER_NICKNAME }" readonly></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="USER_PHONE"
				value="${update.USER_PHONE }" readonly></td>
		</tr>
		<tr>
			<td>웹사이트</td>
			<td><input type="text" name="USER_SITE"
				value="${update.USER_SITE }" readonly></td>
		</tr>
		<tr>
			<td>메시지</td>
			<td><input type="text" name="USER_MSG"
				value="${update.USER_MSG }" readonly></td>
		</tr>
		<tr>
			<td>차단여부</td>
			<td>
				<select name="USER_DENIED">
					<option>${update.USER_DENIED }</option>
					<option value="0">0 - 차단해제</option>
					<option value="1">1 - 차단</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>회원가입 날짜</td>
			<td><input type="text" name="USER_JOIN_DATE"
				value="${update.USER_JOIN_DATE }" readonly></td>
		</tr>
		<tr>
			<td>회원등급</td>
			<td>
				<select	name="USER_TYPE">
					<option>${update.USER_TYPE }</option>
					<option value="0">0 - 관리자</option>
					<option value="1">1 - 일반회원</option>
					<option value="2">2 - 판매자</option>
					<option value="3">3 - 에디터</option>
				</select>
			</td>
		</tr>

	</table>
	<button type="submit">수정완료</button>
</form>	
	<a href='/admin/detail?USER_EMAIL=${update.USER_EMAIL }'
		style="float: right; color: #000000; text-decoration: none;"><h3><< 취소</h3></a>
</div>