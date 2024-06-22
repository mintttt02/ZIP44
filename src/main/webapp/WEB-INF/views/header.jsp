<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<link rel="stylesheet" href="<c:url value="/resources/css/header.css" />">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<!-- header 전체 DIV -->
<div class="header_wrap">
	<div class="header_header">
		<div class="header_navigation" id="header_navigation">
			<!-- 로고 -->
			<div class="header_logo">
				<a href="/">
					<div style="padding-top:10px;">
						<img src="/resources/images/logo.png">
					</div>
				</a>
			</div>
			<!-- 로고 -->
			
			<!-- 우측 메뉴 -->
			<div class="header_login">
				<ul>
					<li>
						<a href="/etc/search" style="padding:0px 12px;">
							<img alt="검색" src="/resources/images/search_icon.svg">
						</a>
					</li>
					<li>
						<c:if test="${email ne null }">
						<a href="/member/cart/${email}" style="padding:0px 12px;">
							<img alt="장바구니" src="/resources/images/cart_icon.svg">
						</a>
						</c:if>
						<c:if test="${email eq null }">
						<a href="/member/cart/${email}" onclick="alert('로그인 해주세요.');return false;" style="padding:0px 12px;">
							<img alt="장바구니" src="/resources/images/cart_icon.svg">
						</a>
						</c:if>
					</li>
					<li><a href="/nonMember" style="padding:0px 12px;">주문조회</a></li>
					<!-- 로그인 버튼 -->
					<li class="aa">
						<!-- 로그인 하지 않은 경우 -->
						<c:if test="${email eq null }">
							<a href="#" onclick="location.href='/member/login';">
								<div class="header_login_btn">로그인/회원가입</div>
							</a>
						</c:if>
						<!-- 로그인 한 경우 -->
						<c:if test="${email ne null }">
							<%@include file="header_login.jsp" %>
						</c:if>
					</li>
					<!-- 로그인 버튼 -->
				</ul>
			</div>
			<!-- 우측 메뉴 -->
			
			<!-- 중앙 메뉴 -->
			<div class="header_menu" id="header_menu">
				<ul>
					<li class="header_list"><a href="/">홈</a></li>
					<li class="header_list"><a href="/magazine/magazine">매거진</a></li>
					<li class="header_list"><a href="/store/">스토어</a></li>
					<li class="header_list"><a href="/etc/photo">사진</a></li>
					<li class="header_list"><a href="/story/story">스토리</a></li> 
				</ul>
			</div>
			<!-- 중앙 메뉴 -->
		</div>
	</div>
</div>
<!-- header 전체 DIV -->