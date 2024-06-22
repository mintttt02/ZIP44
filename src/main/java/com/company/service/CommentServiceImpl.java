package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.persistence.CommentDAO;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private CommentDAO dao;
	
	@Override
	public List<Map<String, Object>> listComment(Integer st_num) throws Exception {
		
		return dao.list(st_num);
	}

	@Override
	public void addComment(Map<String, Object> map) throws Exception {
		
		dao.create(map);
	}

	@Override
	public void modifyComment(Map<String, Object> map) throws Exception {
		
		dao.update(map);
	}

	@Override
	public void removeComment(Integer st_com_num) throws Exception {
		
		dao.delete(st_com_num);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return dao.searchReportUser(map);
	}

	@Override
	public void st_com_report(Map<String, Object> map) throws Exception {
		dao.st_com_report(map);
		
	}

}
