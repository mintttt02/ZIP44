<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ include file="/WEB-INF/views/header.jsp" %>
<!-- 작성자 : 최나현 -->
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.story_item {
	margin : auto;
	margin-top : 120px;
	width : 600px;
	border:1px solid #e5e6e9;
	border-radius:3px;
	margin-bottom:20px;
	background:white;
}

.writer_profile {
	display:inline-block;
	vertical-align:top;
	margin-top:10px;
	margin-left:10px;
}
.writer_profile img {
	object-fit : cover;
	border-radius:50%;
	width:50px;
	height:50px;
}
.writer_info {
	display:inline-block;
	vertical-align:top;
	padding-left:6px;
	margin-top:10px;
}
.writer_name {
	padding-top:4px;
	display:block;
	font-size:14px;
	font-weight:900;
}
.writer_datetime {
	display:block;
	font-size:11px;
	padding-top:4px;
	color:#8c8c8c;
}

.content_description {
	white-space:normal;
	font-size:14px;
	line-height:22px;
	box-sizing:border-box;
	padding:5px 10px 20px 10px;
}
.content_gallery {
	margin-bottom:20px;
	position:relative;
}
.sliderDiv img{
	margin : 10px;
	width : 100px;
	float : left;
}

.story_reply {
   clear : left;
	border-top:1px solid #f2f2f2;
	background:#fafafa;
	padding:10px;
	box-sizing:border-box;
	font-size:12px;
	border-bottom-left-radius:4px;
	border-bottom-right-radius:4px;
}
.reply_write table {
	width:100%;
	border-spacing:0;
    border-collapse:collapse;
}
/*스토리 댓글*/
.comWImg{
	object-fit : cover;
	border-radius:50%;
	width:20px;
	height:20px;
}
.story_item ul{
  list-style:none;
}
a{
text-decoration: none;
color: black;
}
.story_item h3,p{
text-align: center;
}
</style>
</head>
<body>

<div class="story_item" data-st_num = "${st.st_num}">
		<h3>신고된 스토리</h3>
		<p>스토리번호 : ${st.st_num}</p>
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
			
		</div>
		<div class="story_content">
			<div class="content_description">
				<pre>${st.st_content }</pre>
			</div>
			<div class="content_gallery">
			<!-- 스토리이미지영역 -->
				<c:forEach items="${storyImgList}" var="stImg">
					<div class=sliderDiv>
						<img alt="storyImg" src="/zipImg/${stImg.st_img}">
					</div>
				</c:forEach>
			</div>
		</div>
		<!-- 댓글 영역  -->
		<div class="story_reply">
				<div class="reply_write">
					<table>
						<!-- 댓글목록 출력 -->
						<tr>
						<td>
						<ul id="replies${st.st_num}" class="replies">
							<c:forEach items="${storyCommList}" var="sc">
 							<li class='replyLi' >
 							<span>댓글번호 : ${sc.st_com_num}</span>
 	 						<img class="comWImg" alt="댓쓴이 프로필" src="/zipImg/${sc.USER_IMG}">
 	 						<span>${sc.USER_NICKNAME} : ${sc.st_com_content} </span>
 	 						</li>
							</c:forEach>
						</ul>
						</td>
						</tr>
					</table>
				</div>
		</div>
	</div>

</body>
</html>