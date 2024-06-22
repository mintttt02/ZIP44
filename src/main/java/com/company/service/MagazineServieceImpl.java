package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.common.CommandMap;
import com.company.persistence.MagazineDAO;

@Service
public class MagazineServieceImpl implements MagazineService{

	@Inject
	private MagazineDAO dao;
	
	@Override
	public void createMag(Map<String, Object> map) throws Exception {
		dao.createMag(map);
	}

	@Override
	public Map<String, Object> readMag(Integer mag_num) throws Exception {
		return dao.readMag(mag_num);
	}

	@Override
	public void updateMag(Map<String, Object> map) throws Exception {
		dao.updateMag(map);
	}

	@Override
	public void deleteMag(Map<String, Object> map) throws Exception {
		dao.deleteMag(map);
	}

	@Override
	public void addComment(Map<String, Object> map) throws Exception {
		dao.addComment(map);
	}

	@Override
	public List<Map<String, Object>> listComment(Integer mag_num) throws Exception {
		return dao.listComment(mag_num);
	}

	@Override
	public void removeComment(Integer mag_com_num) throws Exception {
		dao.removeComment(mag_com_num);
	}

	@Override
	public void updateComment(Map<String, Object> map) throws Exception {
		dao.updateComment(map);
		
	}

	@Override
	public void updateViewCnt(int mag_num) throws Exception {
		dao.updateViewCnt(mag_num);
		
	}

	@Override
	public List<Map<String, Object>> listOpMag(Map<String, Object> map) throws Exception {
		return dao.listOpMag(map);
	}

	@Override
	public void magAllImg(String originalFileName) throws Exception {
		dao.magAllImg(originalFileName);		
	}

	@Override
	public Map<String, Object> magForm2() throws Exception {
		return dao.magForm2();
	}

	@Override
	public void update_from(Map<String, Object> map) throws Exception {
		dao.update_from(map);		
		
	}

	@Override
	public List<Map<String, Object>> form2_search(Map<String, Object> map) throws Exception {
		return dao.form2_search(map);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return dao.searchReportUser(map);
	}

	@Override
	public void magCommReport(Map<String, Object> map) throws Exception {
		dao.magCommReport(map);		
	}

	@Override
	public void updateMagImg(Map<String, Object> map) throws Exception {
		dao.updateMagImg(map);				
	}

	@Override
	public List<Map<String, Object>> updateDetailMagImg(int mag_num) throws Exception {
		return dao.updateDetailMagImg(mag_num);
	}

	
	

}
