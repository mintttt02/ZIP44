<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/magazine_detail.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class=mag_detail_wrap>
<div class=mag_detail>
	
	<c:if test="${email eq magDetail.user_email || type eq '0'}">
	<div class="DelRpBtn">
		<button onclick="delMag()">삭제</button>
		<button onclick="location.href='/magazine/update_page?mag_num=${magDetail.mag_num}'">수정</button>
	</div>
	</c:if>
	<input type=hidden id="user_email" value=${email }>
	<input type=hidden id="type" value=${type }>
	<input type=hidden name="mag_photo" class="mag_photo" value="${magDetail.mag_photo}">
	<input type=hidden name="mag_num" class="mag_num" value="${magDetail.mag_num}">
	<input type=hidden name="mag_viewcnt" value="${magDetail.mag_viewcnt}">
	<div class=mag_date>
	${magDetail.mag_date}
	</div>
	<div>
	<h1 class=mag_title>${magDetail.mag_title}</h1>
	</div>

	<div class="all_tags">
		<span class="mag_house">#${magDetail.mag_house }</span>
		<span class="mag_size">#${magDetail.mag_size }</span>
		<span class="mag_style">#${magDetail.mag_style }</span>
		<span class="mag_tag">#${magDetail.mag_tag }</span>
	</div>

	<div class="datas">
		<span class="data_title">보관함</span>
		<span class="data_cnt"> ${magDetail.mag_savecnt}</span>
		<span class="data_title">댓글</span>
		<span class="data_cnt"> ${magDetail.cntMagComm}</span>
		<span class="data_title">조회수</span>
		<span class="data_cnt"> ${magDetail.mag_viewcnt}</span>
	</div>

	<div class="mag_content">
		${magDetail.mag_content}
	</div>

	</div>
	</div>
	<div class="mag_comm">
		댓글영역
		<div class="mag_owner">
			<div class="mag_owner_profile"> 
				<img src="/zipImg/${magDetail.USER_IMG}" alt="글쓴이프사">
			</div>
			<div class="mag_owner_nickname">${magDetail.USER_NICKNAME}</div>
		</div>
		<div class="mag_comm_container">
			<div class="mag_comm_write">
				
				<!-- 로그인안했을때  -->
				<c:if test="${email eq null }">
					<table style=width:100% >
						<tr>
							<td>
								<div class="mag_comm_input_ne" onclick="alert('로그인해주세요')">댓글을 달아주세요! </div>
							</td>
							<td style="width:10px;"></td>
							<td style="width:60px;">
								<div class="mag_comm_submit">등록</div>
							</td>
						</tr>
					</table>	
				</c:if>	
				<!-- 로그인했을때  -->
				<c:if test="${email ne null }">
				
					<table style=width:100% >
						<tr>
							<td>
								<div class="mag_comm_input">
									<input type="text"	id="mag_com_content" placeholder="댓글을 달아주세요!">
								</div>
							</td>
							<td style="width:10px;"></td>
							<td style="width:60px;">
								<div class="mag_comm_submit">등록</div>
							</td>
						</tr>
					</table>	
				</c:if>	
				<!-- 댓글목록 -->
				<div class="mag_comm_list">
					<div class="mag_comm_item"></div>
				</div>	
			</div>
		</div>
	</div>
<!-- 댓글 업데이트 popup -->
	<div id="re_update" style=background:white >
		<div>
			<input type="text" id="reply_num" class="reply_num"><!-- 글번호 -->
			<input type="text" id="modal_num" class="modal_num"><!-- 댓글번호 -->
			<input type="text" id="replytext" class="replytext"><!-- 댓글내용 -->
		</div>
		<div>
			<button type="button" id="replyModBtn" class=replyModBtn>Modify</button>
			<button type="button" id="replyDelBtn" class=replyDelBtn>Delete</button>
			<button type="button" class="re_update_close">Close</button>
		</div>
	</div>


<script src="/resources/script/magazine_detail.js"></script>
</body>
</html>