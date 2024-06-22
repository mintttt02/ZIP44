package com.company.persistence;

import java.util.List;
import java.util.Map;


public interface CommentDAO {
	
	public List<Map<String, Object>> list(Integer st_num) throws Exception;
	
	public void create(Map<String, Object> map) throws Exception;
	
	public void update(Map<String, Object> map) throws Exception;
	
	public void delete(Integer st_com_num) throws Exception;

	public int searchReportUser(Map<String, Object> map)throws Exception;

	public void st_com_report(Map<String, Object> map)throws Exception;
}
