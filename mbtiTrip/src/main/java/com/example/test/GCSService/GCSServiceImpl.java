package com.example.test.GCSService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.GCSDTO.GCSDTO;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class GCSServiceImpl implements GCSService{
	
	@Value("${spring.cloud.gcp.storage.bucket}")
	private String bucketName;
	@Value("${spring.cloud.gcp.storage.credentials.location}")
	private String classPath;
	@Value("${spring.cloud.gcp.storage.project-id}")
	private String projectId;
	
	@Override
	/**
	 * 주어진 MultipartFile 객체를 사용하여 파일을 버킷에 업로드합니다.
	 * @param {MultipartFile} file 업로드할 파일(이미지)을 나타내는 MultipartFile 객체입니다.
	 * @return {String} url 업로드된 파일의 URL입니다.
	 */
	public String uploadObject(MultipartFile file) {
		// try-with-resources를 통해 자동으로 close메소드를 호출
	    try (InputStream keyFile = ResourceUtils.getURL(classPath).openStream()) {
	    	System.out.println(bucketName);
	        Storage storage = StorageOptions.newBuilder()
	                .setCredentials(GoogleCredentials.fromStream(keyFile))
	                .build()
	                .getService();
	        String fileName = getFileName(file.getOriginalFilename());
	        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
	                .setContentType(file.getContentType()).build();

	        Blob blob = storage.create(blobInfo, file.getInputStream());
	        String url = getUrl(bucketName, fileName);
	        return url;
	    } catch (FileNotFoundException e) {
	        e.printStackTrace(); // 파일을 찾을 수 없는 경우 로그 출력
	    } catch (IOException e) {
	        e.printStackTrace(); // IO 예외 발생 시 로그 출력
	    } catch (Exception e) {
	        e.printStackTrace(); // 기타 예외 발생 시 로그 출력
	    }
	    return null; // 예외 발생 시 null 반환
	}




	@Override
	/**
	 * 주어진 MultipartFile 객체를 사용하여 파일을 버킷에 업로드합니다.
	 * @param {String} originalName 업로드할 파일의 고유 이름입니다.
	 * @return {String} fielName UUID와 등록날짜를 추가하여 GCS에 업로드할 최종 파일명입니다.
	 */
	public String getFileName(String originalName) {
		UUID uid =UUID.randomUUID();
		Calendar cal = Calendar.getInstance();
		String year = Integer.toString(cal.get(Calendar.YEAR));
		String month = year + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String path = month +"/"+ new DecimalFormat("00").format(cal.get(Calendar.DATE));
		String fileName = path+"/"+uid+originalName;
		return fileName;
	}


	@Override
	/**
	 * 주어진 MultipartFile 객체를 사용하여 파일을 버킷에 업로드합니다.
	 * @param {String} bucket 업로드에 사용하는 버켓명입니다.
	 * @param {String} fileName getFileName()에서 리턴한 등록할 파일의 이름입니다.
	 * @return {String} url GCS에 이미지가 등록된 주소입니다.
	 */
	public String getUrl(String bucket, String fileName) {
		String defaultUrl ="https://storage.googleapis.com/";
		String url=defaultUrl+bucket+"/"+fileName;
		return url;
	}




	@Override
	/**
	 * 주어진 버킷에서 파일을 삭제합니다.
	 * @param {String} currentURL 삭제할 파일의 전체 주소입니다.
	 */
	public void deleteObject(String currentURL){
		// 삭제할 파일명을 주소에서 분리하는 작업입니다.
		System.out.println(currentURL+"HERE!!!");
		String defaultUrl ="https://storage.googleapis.com/kdt3th_project/";
		int dLen =defaultUrl.length();
		String objectName =currentURL.substring(dLen);
		System.out.println(objectName);
		
		//스토리지에 권한부여 및 삭제작업 입니다.
		try(InputStream keyFile = ResourceUtils.getURL(classPath).openStream()){
			Storage storage = StorageOptions.newBuilder()
	                .setCredentials(GoogleCredentials.fromStream(keyFile))
	                .build()
	                .getService();
			Blob blob = storage.get(bucketName, objectName);
		    if (blob == null) {
		      System.out.println("The object " + objectName + " wasn't found in " + bucketName);
		      return;
		    }
	
		    Storage.BlobSourceOption precondition =
		        Storage.BlobSourceOption.generationMatch(blob.getGeneration());
	
		    storage.delete(bucketName, objectName, precondition);
	
		    System.out.println("Object " + objectName + " was deleted from " + bucketName);
		    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		
	}


	
}
