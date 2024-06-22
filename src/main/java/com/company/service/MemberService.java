package com.company.service;

/*
 * 비즈니스 계층은 고객의 요구사항이 반영되는 영역.
 * 비즈니스 영역에 만들어지는 클래스느 인터페이스는 반드시 요구사항과 일치하도록 설계.
 * 스프링에서 비즈니스 영역은 일반적으로 서비스(Service)라는 이름을 칭함.
 *  1) 요구사항을 메소드로 정리해서 xxxService 인터페이스 정의.
 *  2) xxxServiceImpl이라는 구현 객체를 만들어 줌
 */
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.company.domain.Criteria;

public interface MemberService {
	public void registIpAddr(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> listIpAddr(Criteria cri) throws Exception;
	
	public int countIpAddrPaging(Criteria cri) throws Exception;
	
	public void signup(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> LoginService(Map<String, Object> map) throws Exception;

	public void keepLogin(String email, String sessionId, Date next) throws Exception;
	
	public List<Map<String, Object>> listMember(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> listPaging(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public Map<String, Object> MemberDetail(Map<String, Object> map) throws Exception;
	
	public void UpdateMember(Map<String, Object> map) throws Exception;
	
	public void DeleteMember(Map<String, Object> map) throws Exception;
	
	public void SettingProfile(Map<String, Object> map) throws Exception;
	
	public int CheckEmail(Map<String, Object> map) throws Exception;
	
	public int CheckNickname(Map<String, Object> map) throws Exception;
	
	public void ModifyPassword(Map<String, Object> map) throws Exception;
	
	public void NewAddr(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> ListAddr(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> DetailAddr(Map<String, Object> map) throws Exception;
	
	public void ModifyAddr(Map<String, Object> map) throws Exception;
	
	public void RemoveAddr(Map<String, Object> map) throws Exception;
	
	// 내 주문리스트 갯수
	public int CountOrder(Map<String, Object> map) throws Exception;
	
	// 내 주문리스트 갯수(결제대기)
	public int CountWaitngPay(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> MyOrderList(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> MyOrderDetail(Map<String, Object> map) throws Exception;
	
	// 주문 취소
	public void RemoveMyOrder(Map<String, Object> map) throws Exception;
	
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

	// 장바구니  상품 추가
	public void addCart(Map<String, Object> map) throws Exception;
	
	// 장바구니 리스트 출력
	public List<Map<String, Object>> ListCart(Map<String, Object> map) throws Exception;
	
	// 장바구니 상품 삭제
	public void removeCart(Map<String, Object> map) throws Exception;

	public void reportCancel(Map<String, Object> map)throws Exception;
}
