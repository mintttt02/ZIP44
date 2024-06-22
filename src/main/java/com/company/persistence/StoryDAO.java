package com.company.persistence;

import java.util.List;
import java.util.Map;


public interface StoryDAO {
	
	public void writeStory(Map<String, Object> map) throws Exception;
	
	public List<Map<String,Object>> listStory(Map<String, Object> map) throws Exception;

	public List<Map<String, Object>> storyScrolldown(Integer st_num) throws Exception;

	public void storyDelete(int st_num)throws Exception;

	public void saveStoryImg(Map<String, Object> map)throws Exception;

	public int getStoryNum()throws Exception;

	public List<Map<String, Object>> storyImgList(Map<String, Object> map)throws Exception;

	public List<Map<String, Object>> storyScrolldownImgList(Integer st_num)throws Exception;

	public List<Map<String, Object>> delStoryImgList(int st_num)throws Exception;

	public int searchReportUser(Map<String, Object> map)throws Exception;

	public void stReport(Map<String, Object> map)throws Exception;

	public int updateLike(Map<String, Object> map)throws Exception;

}
