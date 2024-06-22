<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/header.jsp" %>

<link rel="stylesheet" href="/resources/css/newAddress.css">

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
function searchZipcode() {
    new daum.Postcode({
        oncomplete: function(data) {
        	 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = ''; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                fullAddr = data.roadAddress;

            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                fullAddr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
            if(data.userSelectedType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('juso1').value = fullAddr;

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById('juso2').focus();
        }
    }).open();
}

</script>

<div class="newAddr_wrap">
	<div class="newAddr_container">
		<form action="/member/address/newPost" method="post">
		<div class="newAddr name">
			<div class="newAddr_subtitle">
				이름
				<span class="red">*</span>
			</div>
			<div class="newAddr_input name">
				<input type="hidden" name="USER_EMAIL" value="${email}">
				<input type="text" name="add_name" value="${addr.USER_NAME}" placeholder="이름">
			</div>
		</div>
		<div class="newAddr_underline"></div>
		<div class="newAddr address">
			<div class="newAddr_subtitle">
				주소
				<span class="red">*</span>
			</div>
			<div class="newAddr_input Addr">
				<div class="newAddr_Addrwarp">
					<div class="newAddr_postNo">
						<input type="text" name="post_code" id="zipcode" readonly placeholder="우편번호">
					</div>
					<div class="newAddr_search_postNo">
						<div style="padding:0 0 0 3px;">
							<div class="search_zipcode" onclick="searchZipcode();">우편번호찾기</div>
						</div>
					</div>
					<div class="newAddr_juso">
						<input type="text" name="juso1" id="juso1" readonly placeholder="주소">
					</div>
					<div class="newAddr_juso">
						<input type="text" name="juso2" id="juso2" value placeholder="주소">
					</div>
				</div>
			</div>
		</div>
		<div class="newAddr_underline"></div>
		<div class="newAddr tel">
			<div class="newAddr_subtitle">
				일반전화
			</div>
			<div class="newAddr_input telNo">
				<div class="select_telNo">
					<select class="tel" name="tel1">
						<option value="02">02</option>
						<option value="031">031</option>
						<option value="032">032</option>
						<option value="033">033</option>
						<option value="041">041</option>
						<option value="042">042</option>
						<option value="043">043</option>
						<option value="051">051</option>
						<option value="052">052</option>
						<option value="053">053</option>
						<option value="054">054</option>
						<option value="055">055</option>
						<option value="061">061</option>
						<option value="062">062</option>
						<option value="063">063</option>
						<option value="064">064</option>
						<option value="070">070</option>
					</select>
					<span></span>
				</div>
				 - 
				 <input type="text" class="tel" name="tel2" maxlength="4">
				 - 
				 <input type="text" class="tel" name="tel3" maxlength="4">
			</div>
		</div>
		<div class="newAddr_underline"></div>
		<div class="newAddr phone">
			<div class="newAddr_subtitle">
				휴대전화
				<span class="red">*</span>
			</div>
			<div class="newAddr_input phoneNo">
				<div class="select_phoneNo">
					<select class="tel" name="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					<span></span>
				</div>
				 - 
					 <input type="text" class="tel" name="phone2" maxlength="4">
					 - 
					 <input type="text" class="tel" name="phone3" maxlength="4">
			</div>	 
		</div>
		<div class="newAddr_underline"></div>
		<div class="newAddr msg">
			<div class="newAddr_subtitle">
				휴대전화
			</div>
			<div class="newAddr_input textMsg">
				<textarea rows="30" cols="3" name="add_msg" placeholder="배송 전 연락주세요."></textarea>
				<div class="msg_notice">
				! 안전한 배송처 혹은 직접수령이 아닌 경우 택배분실이 발생할 수 있으며, 
				이에 대한 문의는 택배사에 확인 부탁드립니다.
				</div>
			</div>
		</div>
		<div style="width:100%;text-align:center;">
			<button class="newAddr_saveBtn" type="submit">완료</button>
		</div>
		</form>
	</div>
	
</div>
<div style="width:100%;border:1px solid #e6e6e6;"></div>