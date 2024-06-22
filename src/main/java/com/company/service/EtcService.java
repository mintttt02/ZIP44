package com.company.service;

import java.util.List;
import java.util.Map;

public interface EtcService {

	public List<Map<String,Object>> getPhoto(Integer mag_num) throws Exception;

	public Integer getMaxmag_num() throws Exception;

	public List<Map<String,Object>> getPhoto_filter(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> getListMag(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> getListProduct(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> getListitemImg(Map<String, Object> map)throws Exception;
}
