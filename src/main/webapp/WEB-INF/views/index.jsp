<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmf" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="header.jsp" %>

<!-- 작성자 : 최나현  -->

<title>MAISON</title>
<link rel="stylesheet" href="<c:url value="/resources/css/index.css"/>">
<script src="https://code.jquery.com/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>

<div class=index>
		<!-- 인덱스 슬라이더  -->
	<div class=index_mm>
		<div class="index_slider">
			<div>
				<img src="./resources/images/main1.jpg" alt="main1">
			</div>
			<div>
				<img src="/resources/images/main2.png" alt="main2">
			</div>
			<div>
				<img src="/resources/images/main3.jpg" alt="main3">
			</div>
			<div>
				<img src="/resources/images/main4.jpg" alt="main4">
			</div>
			<div>
				<img src="/resources/images/main5.jpg" alt="main5">
			</div>
		</div>
	</div>
	
	<div class=index_bb>
	
	<div class=i_m_for>
	<c:forEach items="${listMag}" begin="0" end="9" var="ma">
		<div class=index_m> <!-- 인덱스 매거진  -->
			<div class=index_title> 
				<img src="resources/images/i_home_star.png" alt="magazine_icon">
				${ma.mag_title}
			</div>
			
			<div class=index_p>
				<a href='/magazine/magazine_detail?mag_num=${ma.mag_num}'>
				<img src="/zipImg${ma.mag_photo}" alt="magazine">
				</a>
			</div>
			
			<div class=index_info>
				<div class=i_title>${ma.mag_title}</div>
				<div class=i_tag>
					<a href="#"><span class="mag_house">#${ma.mag_house }</span></a>
						<a href="#"><span class="mag_size">#${ma.mag_size }</span></a>
						<a href="#"><span class="mag_style">#${ma.mag_style }</span></a>
						<a href="#"><span class="mag_tag">#${ma.mag_tag }</span></a>
				</div>
			</div>
		</div>
		</c:forEach>
		</div>
		
		<!--매거진과 상품 중간에 마진 div -->
		<div class=index_bb_margin></div>
		
		
	<div class=i_m_for>
	<c:forEach items="${listSt}" begin="0" end="11" var="st">
		<div class=index_m><!-- 인덱스 상품   -->
			<div class=index_title>
				<img src="resources/images/i_home_furniture.png" alt="store_icon">
				${st.pr_title}
			</div>
			
			<div class=index_p>
				<a href="/store/view/${st.pr_num}">
				<img src="/zipImg${st.pr_img}" alt="product">
				</a>
			</div>
			
			<div class=index_info>
				<div class=i_title>${st.pr_title}</div>
				<div class=i_price>
					<c:set var="price" value="${st.pr_price - (st.pr_price * (st.pr_sale * 0.01))}"></c:set>
					<span><fmf:formatNumber value="${st.pr_price}" type="number" />원</span>
					<span><fmf:formatNumber value="${price}" type="number" />원</span>
					<span>(${st.pr_sale}%)</span>
				</div>
			</div>
		</div>
	</c:forEach>
	</div>
		

	</div>

</div>


<%@ include file="/WEB-INF/views/footer.jsp" %>
<script src="/resources/script/index.js"></script>
