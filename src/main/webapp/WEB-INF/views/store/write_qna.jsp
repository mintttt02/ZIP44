<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>
<link rel="stylesheet" href="/resources/css/write_qna.css">

<script>
	function spaceCheck() {
		if($("input[name=qq_title]").val() == "") {
			alert("제목을 입력해주세요.");
		} else if($("input[name=qq_content]").val() == "") {
			alert("내용을 입력해주세요.");
		} else {
			alert("등록완료!")
			document.getElementById('frm').submit();
		}
	}
</script>

<div class="qna_container">
	<form id="frm" method="post" action="/store/write_qna/add">
		<div class="qna_write_review">
			<input type="text" name="qq_title" placeholder="제목">
			<div class="qna_textarea">
				<textarea name="qq_content" placeholder="Q&A를 작성해 주세요."></textarea>
			</div>
		</div>
		
		<input type="hidden" name="bi_category" value="${pd.bi_category}">
		<input type="hidden" name="pr_num" value="${pd.pr_num}">
		<input type="hidden" name="USER_EMAIL" value="${email}">
		<input type="hidden" name="USER_NAME" value="${name}">
	<div class="qna_btn" onclick="spaceCheck();">등록하기</div>
	</form>
</div>