package com.company.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.company.mapper.CommentMapper";
	
	@Override
	public List<Map<String, Object>> list(Integer st_num) throws Exception {
		return session.selectList(namespace + ".list", st_num);
	}

	@Override
	public void create(Map<String, Object> map) throws Exception {
		
		session.insert(namespace + ".create", map);
	}

	@Override
	public void update(Map<String, Object> map) throws Exception {
		
		session.update(namespace + ".update", map);
	}

	@Override
	public void delete(Integer st_com_num) throws Exception {
		
		session.delete(namespace + ".delete", st_com_num);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".searchReportUser", map);
	}

	@Override
	public void st_com_report(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".st_com_report", map);
		
	}

}
