package com.company.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.domain.Criteria;
import com.company.persistence.MemberDAO;

@Service	// 스프링의 빈으로 인식되기 위해서 @Service를 써줘야 함
public class MemberServiceImpl implements MemberService {
	
	@Inject
	private MemberDAO dao;
	
	@Override
	public void registIpAddr(Map<String, Object> map) throws Exception {
		dao.insertIpAddr(map);
	}
	
	@Override
	public List<Map<String,Object>> listIpAddr(Criteria cri) throws Exception {
		return dao.selectIpAddr(cri);
	}
	
	@Override
	public int countIpAddrPaging(Criteria cri) throws Exception {
		return dao.countIpAddr(cri);
	}
	
	@Override
	public void signup(Map<String, Object> map) throws Exception {
		dao.signup(map);
	}
	
	@Override
	public Map<String, Object> LoginService(Map<String, Object> map) throws Exception {
		
		return dao.login(map);
	}
	
	@Override
	public void keepLogin(String email, String sessionId, Date next) throws Exception {
		
		dao.keepLogin(email, sessionId, next);
	}
	
	@Override
	public List<Map<String, Object>> listMember(Map<String, Object> map) throws Exception {
		return dao.listAll(map);
	}
	
	@Override
	public List<Map<String, Object>> listPaging(Criteria cri) throws Exception {
		
		return dao.listCriteria(cri);
	}
	
	@Override
	public int listCountCriteria(Criteria cri) throws Exception {
		
		return dao.countPaging(cri);
	}
	
	@Override
	public Map<String, Object> MemberDetail(Map<String, Object> map) throws Exception {
		
		return dao.detail(map);
	}
	
	@Override
	public void UpdateMember(Map<String, Object> map) throws Exception {
		dao.update(map);
	}
	
	@Override
	public void DeleteMember(Map<String, Object> map) throws Exception {
		
		dao.delete(map);
	}
	
	@Override
	public void SettingProfile(Map<String, Object> map) throws Exception {
		dao.updateProfile(map);
	}
	
	@Override
	public int CheckEmail(Map<String, Object> map) throws Exception {
		return dao.emailChk(map);
	}
	
	@Override
	public int CheckNickname (Map<String, Object> map) throws Exception {
		return dao.nickChk(map);
	}
	
	@Override
	public void ModifyPassword(Map<String, Object> map) throws Exception {
		dao.updatePassword(map);
	}
	
	@Override
	public void NewAddr(Map<String, Object> map) throws Exception {
		dao.insertAddr(map);
	}
	
	@Override
	public List<Map<String, Object>> ListAddr(Map<String, Object> map) throws Exception {
		return dao.selectAddr(map);
	}
	
	@Override
	public Map<String, Object> DetailAddr(Map<String, Object> map) throws Exception {
		return dao.selectOneAddr(map);
	}
	
	@Override
	public void ModifyAddr(Map<String, Object> map) throws Exception {
		dao.updateAddr(map);
	}
	
	@Override
	public void RemoveAddr(Map<String, Object> map) throws Exception {
		dao.deleteAddr(map);
	}
	
	@Override
	public int CountOrder(Map<String, Object> map) throws Exception {
		return dao.selectMyOrder(map);
	}
	
	@Override
	public int CountWaitngPay(Map<String, Object> map) throws Exception {
		return dao.selectWatingPay(map);
	}
	
	@Override
	public List<Map<String, Object>> MyOrderList(Map<String, Object> map) throws Exception {
		return dao.selectMyOrderList(map);
	}
	
	@Override
	public Map<String, Object> MyOrderDetail(Map<String, Object> map) throws Exception {
		return dao.selectMyOrderDetail(map);
	}
	
	// 주문 취소
	@Override
	public void RemoveMyOrder(Map<String, Object> map) throws Exception {
		dao.deleteMyOrder(map);
	}
	
	/*신고페이징*/ 
	@Override
	public List<Map<String, Object>> reportList(Criteria cri) throws Exception {
		return dao.reportList(cri);
	}

	@Override
	public int reportListCnt(Criteria cri) throws Exception {
		return dao.reportListCnt(cri);
	}

	@Override
	public List<Map<String, Object>> magtitle(Criteria cri) throws Exception {
		return dao.magtitle(cri);
	}

	@Override
	public Map<String, Object> storyReportDetail(Integer st_num) throws Exception {
		return dao.storyReportDetail(st_num);
	}

	@Override
	public List<Map<String, Object>> storyReportImgList(Integer st_num) throws Exception {
		return dao.storyReportImgList(st_num);
	}

	@Override
	public List<Map<String, Object>> storyCommList(Integer st_num) throws Exception {
		return  dao.storyCommList(st_num);
	}

	@Override
	public List<Map<String, Object>> storyNum(Criteria cri) throws Exception {
		return dao.storyNum(cri);
	}

	@Override
	public void reportDel(Map<String, Object> map) throws Exception {
		dao.reportDel(map);
		
	}

	@Override
	public Integer tmpPsw(Map<String, Object> map) throws Exception {
		return dao.tmpPsw(map);
	}
	
	@Override
	public void addCart(Map<String, Object> map) throws Exception {
		dao.insertCart(map);
	}
	
	@Override
	public List<Map<String, Object>> ListCart(Map<String, Object> map) throws Exception {
		return dao.selectCartList(map);
	}
	
	@Override
	public void removeCart(Map<String, Object> map) throws Exception {
		dao.deleteCart(map);
	}

	@Override
	public void reportCancel(Map<String, Object> map) throws Exception {
		dao.reportCancel(map);
		
	}
}
