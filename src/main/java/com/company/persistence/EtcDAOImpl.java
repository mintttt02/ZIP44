package com.company.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class EtcDAOImpl implements EtcDAO{

	@Inject
	private SqlSession session;
	
	private static String namespace = "com.company.mapper.EtcMapper";

	@Override
	public List<Map<String, Object>> getPhoto(Integer mag_num) throws Exception {
		return session.selectList(namespace+".getPhoto",mag_num);
	}

	@Override
	public Integer getMaxmag_num() throws Exception {
		return session.selectOne(namespace+".getMaxmag_num");
	}

	@Override
	public List<Map<String, Object>> getPhoto_filter(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".getPhoto_filter",map);
	}

	@Override
	public List<Map<String, Object>> getListMag(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".getListMag",map);
	}

	@Override
	public List<Map<String, Object>> getListProduct(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".getListProduct",map);
	}

	@Override
	public List<Map<String, Object>> getListitemImg(Map<String, Object> map) throws Exception {
		return session.selectList(namespace+".getListitemImg",map);
	}
}
