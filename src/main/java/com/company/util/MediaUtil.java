package com.company.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;


/*저장한 파일이 이미지 타입의 파일이라면? -> 썸네일 이미지 생성 - > 생성된 썸네일 파일이름 반환 
 * 
 * 확장자를 가지고 이미지 타입인지 판단해주는 역할 
 * 별도의 클래스로 구성한 이유는 브라우저에서 파일을 다운로드 할건지 보여줄건지 결정하기 위해서.*/

public class MediaUtil {
	private static Map<String, MediaType> mediaMap; 
	
	static {
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
		
	}
	
	public static MediaType getMediaType(String type) {
		return mediaMap.get(type.toUpperCase());
	}

}
