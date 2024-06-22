<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/views/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
<script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/story.css" />">


<!-- 작성자 : 조현호,최나현 -->
<input type="hidden" name="USER_EMAIL" id="user_email" value="${email}">
<input type=hidden id="type" value=${type }>
<!-- 스토리 시작 -->
<div class="story_wrapper">
	<!-- 로그인 하지 않은 경우 -->
		<c:if test="${email eq null}">
		<div class="story_write">
			<div class="write_content">
				<div>우리집에 대한 이야기를 글과 사진으로 남겨주세요 :)</div>
			</div>
			<div class="story_hr">
				<div class="hr_line"></div>
			</div>
			<div class="story_menu">
				<div class="story_menu_left">
					<div>사진선택</div>
				</div>
				<div class="story_menu_right">
					<div class="create_btn" style="line-height:27px">등록</div>
				</div>
			</div>	
		</div>
		</c:if>
		<!-- 로그인 하지 않은 경우 -->
		
	<!-- 로그인 한 경우 스토리글쓰기  -->
	<c:if test="${email ne null }">
		<div class="story_write">
			<div class="write_content">
				<textarea wrap="hard" name="st_content"  id="st_content" placeholder="우리집에 대한 이야기를 글과 사진으로 남겨주세요 :)"></textarea>
			</div>
			<div class="story_hr">
				<div class="hr_line"></div>
				<div class="preview" id="preview"></div>
			</div>
			<div class="story_menu">
				<div class="story_menu_left">
					<input type="file" id="photo_btn" class="photo_btn" name="photo_btn" 
					multiple accept="image/*" >사진선택
				</div>
				<div class="story_menu_right">
					<input type="button" class="create_btn" id="create_btn" value="등록">
				</div>
			</div>
		</div>
	</c:if>
	
	 
	 
	 
	<!-- 스토리 리스트  -->	
	<div class="storyrepeat">
	<c:forEach items="${storylist}" var="st">
	<div class="story_item" data-st_num = "${st.st_num}">
		<div class="story_writer">
			<div class="writer_profile">
				<img alt="작성자 프로필" src="/zipImg/${st.USER_IMG}">
			</div>
			<div class="writer_info">
				<div class="writer_name">
					<a href="">${st.USER_NICKNAME}</a>
				</div>
				<div class="writer_datetime">
					${st.st_date }
				</div>
			</div>
			<div class="writer_option">
				<div class="writer_report_btn" data-balloon="신고">
					<img alt="신고버튼" src="/resources/images/flag.png">
				</div>
				
				<div class="storyDelBtnDiv" data-stWemail="${st.USER_EMAIL }">
					<c:if  test="${email eq st.user_email || type eq '0'}">
					<span class="storyDeleteBtn" ><img src='/resources/images/if_trash_bin_1370026.png'></span> <!-- 삭제버튼  -->
					</c:if>
				</div>
					
			</div>
		</div>
		<div class="story_content">
			<div class="content_description">
				<pre>${st.st_content }</pre>
			</div>
			<div class="content_gallery">
						<div class="content_gallery_item" data-st_num="${st.st_num}">
							<!-- 스토리이미지영역 -->
							<c:forEach items="${storyImgList}" var="stImg">
							<c:if test="${st.st_num eq stImg.st_num}">
							<div class=sliderDiv>
								<img alt="storyImg" src="/zipImg/${stImg.st_img}">
							</div>
							</c:if>
							</c:forEach>
						</div>
			</div>
		</div>
		<div class="story_like">
			<input type="hidden" value="N" class="stroy_data">
			<img class="like_icon" alt="좋아요" src="/resources/images/like_icon.png">
			<span class="like_cnt" >${st.st_like}</span>
		</div>
		
		<!-- 댓글 영역  -->
		<div class="story_reply">
				<div class="reply_write">
						<input type="hidden" name="st_num" class="st_num" id="st_num" value="${st.st_num}">		
					<table>
						<tr>
							<td>	
								<input type="text" name="st_com_content" id="st_com_content" class="reply_input" 
								placeholder="댓글을 입력하세요!">
							</td>
							<td style="width:10px;"></td>
							<td style="width:60px;">
								<button style="opacity:1" id="reply_write_btn" class="reply_write_btn" >등록</button>
							</td>
						</tr>
						<!-- 댓글목록 출력 -->
						<tr>
						<td>
						<ul id="replies${st.st_num}" class="replies"></ul>
						</td>
						</tr>
					</table>
				</div>
			<div class="story_reply_wrapper" id="story_reply_wrapper">
			</div>
		</div>
	</div>
	</c:forEach>
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
<!-- 스토리 끝 -->

<script src="/resources/script/story.js"></script>
						
