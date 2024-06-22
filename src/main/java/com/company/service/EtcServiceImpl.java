package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.persistence.EtcDAO;

@Service
public class EtcServiceImpl implements EtcService{

	@Inject
	private EtcDAO dao;

	
	@Override
	public List<Map<String, Object>> getPhoto(Integer mag_num) throws Exception {
		return dao.getPhoto(mag_num);
	}

	@Override
	public Integer getMaxmag_num() throws Exception {
		return dao.getMaxmag_num();
	}

	@Override
	public List<Map<String, Object>> getPhoto_filter(Map<String, Object> map) throws Exception {
		return dao.getPhoto_filter(map);
	}

	@Override
	public List<Map<String, Object>> getListMag(Map<String, Object> map) throws Exception {
		return  dao.getListMag(map);
	}

	@Override
	public List<Map<String, Object>> getListProduct(Map<String, Object> map) throws Exception {
		return dao.getListProduct(map);
	}

	@Override
	public List<Map<String, Object>> getListitemImg(Map<String, Object> map) throws Exception {
		return dao.getListitemImg(map);
	}
}
