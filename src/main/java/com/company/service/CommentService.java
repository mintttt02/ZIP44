package com.company.service;

import java.util.List;
import java.util.Map;

public interface CommentService {
	
	public List<Map<String, Object>> listComment(Integer st_num) throws Exception;
	
	public void addComment(Map<String, Object> map) throws Exception;
	
	public void modifyComment(Map<String, Object> map) throws Exception;
	
	public void removeComment(Integer st_com_num) throws Exception;

	public int searchReportUser(Map<String, Object> map)throws Exception;

	public void st_com_report(Map<String, Object> map)throws Exception;
}
