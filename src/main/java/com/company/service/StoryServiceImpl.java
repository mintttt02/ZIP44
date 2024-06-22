package com.company.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.company.persistence.StoryDAO;

@Service
public class StoryServiceImpl implements StoryService {
	
	@Inject
	private StoryDAO dao;
	
	@Override
	public void registStory(Map<String, Object> map) throws Exception {
		
		dao.writeStory(map);	
	}
	
	@Override
	public List<Map<String, Object>> listStory(Map<String, Object> map) throws Exception {
		
		return dao.listStory(map);
	}

	@Override
	public List<Map<String, Object>> storyScrolldown(Integer st_num) throws Exception{
		return dao.storyScrolldown(st_num);
	}

	@Override
	public void storyDelete(int st_num) throws Exception{
		dao.storyDelete(st_num);
	}

	@Override
	public void saveStoryImg(Map<String, Object> map) throws Exception{
		dao.saveStoryImg(map);
	}

	@Override
	public int getStoryNum() throws Exception {
		return dao.getStoryNum();
		
	}

	@Override
	public List<Map<String, Object>> storyImgList(Map<String, Object> map) throws Exception {
		return dao.storyImgList(map);
	}

	@Override
	public List<Map<String, Object>> storyScrolldownImgList(Integer st_num) throws Exception {
		return dao.storyScrolldownImgList(st_num);
	}

	@Override
	public List<Map<String, Object>> delStoryImgList(int st_num) throws Exception {
		return dao.delStoryImgList(st_num);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return dao.searchReportUser(map);
	}

	@Override
	public void stReport(Map<String, Object> map) throws Exception {
		dao.stReport(map);
		
	}
	@Override
	public int updateLike(Map<String, Object> map) throws Exception {
		return dao.updateLike(map);
	}
}
