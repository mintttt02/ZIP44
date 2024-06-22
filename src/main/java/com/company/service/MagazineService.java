package com.company.service;

import java.util.List;
import java.util.Map;

import com.company.common.CommandMap;


public interface MagazineService {

	public void createMag(Map<String, Object> map) throws Exception;
	
	public Map<String, Object> readMag(Integer mag_num) throws Exception;
	
	public void updateMag(Map<String, Object> map)throws Exception;
	
	public void deleteMag(Map<String, Object> map)throws Exception;
	
	public void addComment(Map<String, Object> map)throws Exception;

	public List<Map<String,Object>> listComment(Integer mag_num)throws Exception;

	public void removeComment(Integer mag_com_num)throws Exception;

	public void updateComment(Map<String, Object> map)throws Exception;

	public void updateViewCnt(int mag_num)throws Exception;

	public List<Map<String, Object>> listOpMag(Map<String, Object> map)throws Exception;

	public void magAllImg(String originalFileName)throws Exception;

	public Map<String, Object> magForm2()throws Exception;

	public void update_from(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> form2_search(Map<String, Object> map)throws Exception;

	public int searchReportUser(Map<String, Object> map)throws Exception;

	public void magCommReport(Map<String, Object> map)throws Exception;

	public void updateMagImg(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> updateDetailMagImg(int mag_num)throws Exception;




	
}
