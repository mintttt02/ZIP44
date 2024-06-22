package com.company.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class StoryDAOImpl implements StoryDAO {
	@Inject
	private SqlSession session; // root-context.xml에서 설정
	
	private static String namespace = "com.company.mapper.StoryMapper";
	
	@Override
	public void writeStory(Map<String, Object> map) throws Exception {
		session.insert(namespace + ".writeStory", map); 
	}
	
	@Override
	public List<Map<String,Object>> listStory(Map<String, Object> map) throws Exception {
		return session.selectList(namespace + ".listStory");
	}
	
	@Override
	public List<Map<String,Object>> storyScrolldown(Integer st_num) throws Exception {
		return session.selectList(namespace + ".storyScrolldown",st_num);
	}

	@Override
	public void storyDelete(int st_num) throws Exception {
		session.delete(namespace+".storyDelete",st_num);
	}

	@Override
	public void saveStoryImg(Map<String, Object> map) throws Exception{
		session.insert(namespace+".saveStoryImg", map);
	}

	@Override
	public int getStoryNum() throws Exception {
		return session.selectOne(namespace+".getStoryNum");
		
	}

	@Override
	public List<Map<String, Object>> storyImgList(Map<String, Object> map) {
		return session.selectList(namespace + ".storyImgList",map);
	}

	@Override
	public List<Map<String, Object>> storyScrolldownImgList(Integer st_num) throws Exception {
		return session.selectList(namespace + ".storyScrolldownImgList",st_num);
	}

	@Override
	public List<Map<String, Object>> delStoryImgList(int st_num) throws Exception {
		return session.selectList(namespace + ".delStoryImgList",st_num);
	}

	@Override
	public int searchReportUser(Map<String, Object> map) throws Exception {
		return session.selectOne(namespace + ".searchReportUser",map);
	}

	@Override
	public void stReport(Map<String, Object> map) throws Exception {
		session.insert(namespace+".stReport", map);
		
	}

	@Override
	public int updateLike(Map<String, Object> map) throws Exception {
		int result = session.selectOne(namespace + ".updateSt_like_cnt",map);
		if(result == 0) {
			session.insert(namespace+".updateSt_like", map);
			session.update(namespace+".updateLike", map);
			return result; //result = 0 좋아요 안함 
		}
		return result;
	}
	
	
}
