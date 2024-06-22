package com.company.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.company.common.CommandMap;


@Repository
public class MagazineDAOImpl implements MagazineDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.company.mapper.MagazineMapper";
	

	@Override
	public void createMag(Map<String, Object> map) throws Exception {
		session.insert(namespace+".createMag",map);
	}

	@Override
	public Map<String, Object> readMag(Integer mag_num) throws Exception {
				
		 Map<String, Object> map = session.selectOne(namespace+".readMag",mag_num);
		 int cntMagComm = session.selectOne(namespace+".cntMagComm",mag_num);
		 map.put("cntMagComm", cntMagComm);
		 
		return map;
	}

	@Override
	public void updateMag(Map<String, Object> map) throws Exception {
		session.update(namespace+".updateMag",map);
		session.delete(namespace+".updateBeforeDeleteMagImg",map);
		//수정된 이미지들 다시 넣기 전에 기존에 있던 내용 지움.
	}

	@Override
	public void deleteMag(Map<String, Object> map) throws Exception {
		session.delete(namespace+".deleteMag",map);
	}


	@Override
	public void addComment(Map<String, Object> map) throws Exception {
		session.insert(namespace+".addComment",map);
	}

	@Override
	public List<Map<String, Object>> listComment(Integer mag_num) throws Exception {
		return session.selectList(namespace+".listComment",mag_num);
	}

	@Override
	public void removeComment(Integer mag_com_num) throws Exception {
		session.delete(namespace+".removeComment",mag_com_num);
		
	}

	@Override
	public void updateComment(Map<String, Object> map) throws Exception {
		session.update(namespace+".updateComment",map);
		
	}

	@Override
	public void updateViewCnt(int mag_num) throws Exception {
		session.update(namespace+".updateViewCnt",mag_num);
		
	}

	@Override
	public List<Map<String, Object>> listOpMag(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".listOpMag", map);
	}

	@Override
	public void magAllImg(String originalFileName) throws Exception {
		session.insert(namespace+".magAllImg",originalFileName);		
	}

	@Override
	public Map<String, Object> magForm2() throws Exception {
		return session.selectOne(namespace+".magForm2");
	}

	@Override
	public void update_from(Map<String, Object> map) throws Exception {
		session.update(namespace+".update_from",map);
		
	}

	@Override
	public List<Map<String, Object>> form2_search(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".form2_search", map);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace+".searchReportUser",map);
	}

	@Override
	public void magCommReport(Map<String, Object> map) throws Exception {
		session.insert(namespace+".magCommReport",map);		
		
	}

	@Override
	public void updateMagImg(Map<String, Object> map) throws Exception {
		session.insert(namespace+".updateMagImg",map);		
		
	}

	@Override
	public List<Map<String, Object>> updateDetailMagImg(int mag_num) throws Exception {
		return session.selectList(namespace+".updateDetailMagImg", mag_num);
	}

}
