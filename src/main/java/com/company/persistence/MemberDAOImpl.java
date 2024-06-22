package com.company.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.domain.Criteria;

// MemberDAO 인터페이스를 구현함
// SqlSessionTemplate를 이용해서 원하는 코드를 호출하는 수준
@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession session; // root-context.xml에서 설정
	
	private static String namespace = "com.company.mapper.MemberMapper";	// memberMapper의 주소
	
	@Override
	public void insertIpAddr(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertIpAddr", map);
	}
	
	@Override
	public List<Map<String, Object>> selectIpAddr(Criteria cri) throws Exception {
		return session.selectList(namespace + ".selectIpAddr", cri);
	}
	
	@Override
	public int countIpAddr(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".IpAddrPaging", cri);
	}
	
	@Override
	public void signup(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".signup", map);
	}
	
	@Override
	public Map<String, Object> login(Map<String, Object> map) throws Exception {
		
		return session.selectOne(namespace + ".login", map);
	}
	
	@Override
	public void keepLogin(String email, String sessionId, Date next) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("email", email);
		paramMap.put("sessionId", sessionId);
		paramMap.put("next", next);
		
		session.update(namespace + ".keepLogin", paramMap);
	}
	
	@Override
	public List<Map<String, Object>> listAll(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".listMap");
	}
	@Override
	public List<Map<String, Object>> listCriteria(Criteria cri) throws Exception {
		
		return session.selectList(namespace + ".listCriteria", cri);
	}
	@Override
	public int countPaging(Criteria cri) throws Exception {
		
		return session.selectOne(namespace + ".countPaging", cri);
	}
	
	@Override
	public Map<String, Object> detail(Map<String, Object> map) throws Exception {
		
		return session.selectOne(namespace + ".detail", map);
	}
	
	@Override
	public void update(Map<String, Object> map) throws Exception {
		
		session.update(namespace + ".update", map);
	}
	
	@Override
	public void delete(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".delete", map);
	}
	
	@Override
	public void updateProfile(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateProfile", map); 
	}
	
	@Override
	public int emailChk(Map<String, Object> map) throws Exception {
		int result = session.selectOne(namespace + ".emailChk", map);
		
		return result;
	}
	
	@Override
	public int nickChk(Map<String, Object> map) throws Exception {
		int result = session.selectOne(namespace + ".nickChk", map);
		
		return result;
	}
	
	@Override
	public void updatePassword(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updatePassword", map);
	}
	
	@Override
	public void insertAddr(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertAddr", map);
	}
	
	@Override
	public List<Map<String, Object>> selectAddr(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectAddr", map); 
	}
	
	@Override
	public Map<String, Object> selectOneAddr(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectOneAddr", map);
	}
	
	@Override
	public void updateAddr(Map<String, Object> map) throws Exception {
		session.update(namespace + ".updateAddr", map);
	}
	
	@Override
	public void deleteAddr(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".deleteAddr", map);
	}
	
	@Override
	public int selectMyOrder(Map<String, Object> map) throws Exception {
		int result = session.selectOne(namespace + ".selectMyOrder", map);
		
		return result;
	}
	
	@Override
	public int selectWatingPay(Map<String, Object> map) throws Exception {
		int result = session.selectOne(namespace + ".selectWatingPay", map);
		
		return result;
	}
	
	@Override
	public List<Map<String, Object>> selectMyOrderList(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectMyOrderList", map);
	}
	
	@Override
	public Map<String, Object> selectMyOrderDetail(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".selectMyOrderDetail", map);
	}
	
	// 주문 취소
	@Override
	public void deleteMyOrder(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".deleteMyOrder", map);
	}
	
	/*신고페이징*/ 
	@Override
	public List<Map<String, Object>> reportList(Criteria cri) throws Exception {
		return session.selectList(namespace + ".reportList", cri);
	}

	@Override
	public int reportListCnt(Criteria cri) throws Exception {
		return session.selectOne(namespace + ".reportListCnt", cri);
	}

	@Override
	public List<Map<String, Object>> magtitle(Criteria cri) throws Exception {
		return session.selectList(namespace + ".magtitle", cri);
	}

	@Override
	public Map<String, Object> storyReportDetail(Integer st_num) throws Exception {
		return session.selectOne(namespace + ".storyReportDetail", st_num);
	}

	@Override
	public List<Map<String, Object>> storyReportImgList(Integer st_num) throws Exception {
		return session.selectList(namespace + ".storyReportImgList", st_num);
	}

	@Override
	public List<Map<String, Object>> storyCommList(Integer st_num) throws Exception {
		return session.selectList(namespace + ".storyCommList", st_num);
	}

	@Override
	public List<Map<String, Object>> storyNum(Criteria cri) throws Exception {
		return session.selectList(namespace + ".storyNum", cri);
	}

	@Override
	public void reportDel(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".reportDel", map);
	}

	@Override
	public Integer tmpPsw(Map<String, Object> map) throws Exception {
		System.out.println("createPass DAO="+map.get("createPass"));
		Integer chkEmail= session.selectOne(namespace + ".chkEmail", map);
		if(chkEmail == 1) {
		session.update(namespace + ".tmpPsw", map);
		return 1;
		}else {
			return 0; //해당하는 email이 없음. 
		}
	}
	
	@Override
	public void insertCart(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".insertCart", map);
	}
	
	@Override
	public List<Map<String, Object>> selectCartList(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".selectCartList", map);
	}
	
	@Override
	public void deleteCart(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".deleteCart", map);
	}

	@Override
	public void reportCancel(Map<String, Object> map) throws Exception {
		session.delete(namespace + ".reportCancel", map);
		
	}
}

