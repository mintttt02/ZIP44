package com.company.persistence;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.company.domain.Criteria;

public interface MemberDAO {
	public void insertIpAddr(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectIpAddr(Criteria cri) throws Exception;
	
	public int countIpAddr(Criteria cri) throws Exception;
	
	// 회원가입
	public void signup(Map<String, Object> map) throws Exception;
	
	// 로그인
	public Map<String, Object> login(Map<String, Object> map) throws Exception;
	
	public void keepLogin(String email, String sessionId, Date next);
	
	// 회원리스트(관리자)
	public List<Map<String, Object>> listAll(Map<String, Object> map) throws Exception;
	
	// 페이징을 포함한 회원리스트(관리자)
	public List<Map<String, Object>> listCriteria(Criteria cri) throws Exception;
	
	// 페이징 수 카운트(관리자)
	public int countPaging(Criteria cri) throws Exception;
	
	// 회원 상세정보(관리자)
	public Map<String, Object> detail(Map<String, Object> map) throws Exception;
	
	// 회원 정보 수정(관리자)
	public void update(Map<String, Object> map) throws Exception;
	
	// 회원 정보 삭제(관리자)
	public void delete(Map<String, Object> map) throws Exception;
	
	// 회원 정보 수정
	public void updateProfile(Map<String, Object> map) throws Exception; 
	
	// 이메일 중복체크
	public int emailChk(Map<String, Object> map) throws Exception;
	
	// 닉네임 중복체크
	public int nickChk(Map<String, Object> map) throws Exception;
	
	// 비밀번호 변경
	public void updatePassword(Map<String, Object> map) throws Exception;
	
	// 새 배송지 만들기
	public void insertAddr(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectAddr(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> selectOneAddr(Map<String, Object> map) throws Exception;
	
	public void updateAddr(Map<String, Object> map) throws Exception;
	
	public void deleteAddr(Map<String, Object> map) throws Exception;
	
	// 내 주문리스트 갯수 
	public int selectMyOrder(Map<String, Object> map) throws Exception;
	
	// 내 주문리스트 갯수(결제대기)
	public int selectWatingPay(Map<String, Object> map) throws Exception;
	
	// 내 주문리스트
	public List<Map<String, Object>> selectMyOrderList(Map<String, Object> map) throws Exception;
	
	// 주문 상세내역
	public Map<String, Object> selectMyOrderDetail(Map<String, Object> map) throws Exception;
	
	// 주문 취소
	public void deleteMyOrder(Map<String, Object> map) throws Exception;
	
	/*신고페이징*/ 
	public List<Map<String, Object>> reportList(Criteria cri)throws Exception;

	public int reportListCnt(Criteria cri)throws Exception;

	public List<Map<String, Object>> magtitle(Criteria cri)throws Exception;

	public Map<String, Object> storyReportDetail(Integer st_num)throws Exception;

	public List<Map<String, Object>> storyReportImgList(Integer st_num)throws Exception;

	public List<Map<String, Object>> storyCommList(Integer st_num)throws Exception;

	public List<Map<String, Object>> storyNum(Criteria cri)throws Exception;

	public void reportDel(Map<String, Object> map)throws Exception;

	public Integer tmpPsw(Map<String, Object> map)throws Exception;
	
	// 장바구니 상품 추가 
	public void insertCart(Map<String, Object> map) throws Exception;
	
	// 장바구니 리스트 출력 
	public List<Map<String, Object>> selectCartList(Map<String, Object> map) throws Exception;
	
	// 장바구니 상품 삭제
	public void deleteCart(Map<String, Object> map) throws Exception;

	public void reportCancel(Map<String, Object> map)throws Exception;

}
