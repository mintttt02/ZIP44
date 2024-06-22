package com.company.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/* 파일 업로드 기능처리를 위한 페이지
 * 1. 자동적 폴더생성
 * 2. 파일저장은 UUID를 이용해서 처리
 * 3. 썸네일 이미지 생성 
 * 
 * 처리 순서 
 * 1. uuid를 이용한 고유한 값 생성
 * 2. uuid와 결합한 업로드 파일이름생성
 * 3. 파일이 저장될 '년/월/일' 정보 생성
 * 4. 업로드 기본 경로 (uploadPath)와 '년/월/일' 폴더 생성
 * 5. 기본경로 +폴더경로 + 파일 이름으로 파일 저장 */
public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	/*원본 파일의 이름과 파일데이터를 byte[]로 변환한 정보를 파라미터로 처리해서 실제로 파일을 업로드함*/
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData)
	throws Exception{
		
		UUID uid = UUID.randomUUID();
		/*UUID : 중복되지 않는 고유한 키 값을 설정할 떄 사용 
		 * 동일폴더에 동일한 이름의 파일을 업로드하는 경우를 피하기 위함 */
		String savedPath = calcPath(uploadPath);
		/*저장될 경로 계산 */

		  String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
		  originalName =originalName.replaceAll(match, "");
	      /*파일명 안의 특수문자 제거 코드*/
		  
		originalName = originalName.replaceAll("\\p{Z}", ""); 
		/*파일명안에있는 공백 삭제 코드
		* 공백있으니까 다운로드시 공백부터 파일명이 짤려서 넣음 */
		
		String savedName = uid.toString() + "_" + originalName;
		/*실제 파일업로드 결과 : 파일이름 = UUID값 + _ + 파일원래이름*/
		
		File target = new File(uploadPath + savedPath,savedName);
		
		FileCopyUtils.copy(fileData, target);
		/*원본파일 저장 
		 * FileCopyUtils : 실제 파일 처리 위한 스프링에서 제공하는 클래스 
		 * 파일데이터를 파일로 처리하거나 복사하는 등의 작업에 사용 p.529*/
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		/*원본파일의 확장자 */
		
		String uploadedFileName = null;
		
		
		/*MediaUtil 클래스를 이용하여 이미지 파일인경우와 그렇지 않은경우를  나누어 처리*/
		if(MediaUtil.getMediaType(formatName)!=null) {
//			uploadedFileName = uploadPath+ savedPath + '\\' + savedName;
			/*이미지 파일인경우 썸네일 생성 */
			String FileNameEx = uploadPath + savedPath +File.separator + savedName;
			uploadedFileName = FileNameEx.substring(uploadPath.length()).replace(File.separatorChar, '/');
		}else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;
	}
	
	
	
	
	private static String makeIcon(String uploadPath,String path, String fileName)throws Exception{
		String iconName = uploadPath + path + File.separator+fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
	
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		
		String datePath = monthPath + File.separator + 
				new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		logger.info(datePath);
		return datePath;
		/*최종 결과 폴더를 반환함 
		 * 기본경로를 uploadPath 를 파라미터로 전달받음 
		 * java.util.calendar 클래스 이용, 현재 시스템의 날짜 데이터 받음 
		 * 얻어낸 날짜정보는 기본경로와 함께 makeDir에 전달되어서 폴더가 생성됨. */
	}
	
	private static void makeDir(String uploadPath, String...paths) {
		
		if(new File(paths[paths.length-1]).exists()) {
			return;
		}
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			if(! dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}

	/*썸네일 이미지 생성을 위한 코드
	 * 저장한 파일이 이미지 타입의 파일이라면? -> 썸네일 이미지 생성 - > 생성된 썸네일 파일이름 반환 */
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path, fileName));
		
		BufferedImage destImg = Scalr.resize(sourceImg, 
											Scalr.Method.AUTOMATIC, 
											Scalr.Mode.FIT_TO_HEIGHT,100);
		String thumbnailName = 
				uploadPath + path + File.separator + "s_"+fileName;
		
		File newFile = new File(thumbnailName);
		String formatName = 
				fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	/*BufferedImage : 실제 이미지가 아닌 메모리상의 이미지를 의미하는 객체 
	 * 원본파일을 메모리상으로 로딩, 정해진 크기에 맞게 작은 이미지 파일에 원본이미지를 복사함 
	 * FIT_TO_HEIGHT : 썸네일 이미지 파일의 높이를 100px로 동일하게 만들어줌 
	 * 썸네일 이미지 파일명 -> UUID+s_으로 항상 시작되도록함 
	 * 메소드 리턴시 문자열을 치환하는 이유 : 
	 * 브라우저에서 윈도우의 경로로 사용하는 w 문자가 정상적인 경로로 인식되지 않기때문에 /로 치환 */













}
