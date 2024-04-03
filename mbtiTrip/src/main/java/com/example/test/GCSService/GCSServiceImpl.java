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
	
	
	@Override
	public String uploadObject(GCSDTO dto) {
		// try-with-resources를 통해 자동으로 close메소드를 호출
	    try (InputStream keyFile = ResourceUtils.getURL(classPath).openStream()) {
	    	System.out.println(bucketName);
	        Storage storage = StorageOptions.newBuilder()
	                .setCredentials(GoogleCredentials.fromStream(keyFile))
	                .build()
	                .getService();
	        String fileName = getFileName(dto.getFile().getOriginalFilename());
	        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
	                .setContentType(dto.getFile().getContentType()).build();

	        Blob blob = storage.create(blobInfo, dto.getFile().getInputStream());
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
	public String getUrl(String bucket, String fileName) {
		String defaultUrl ="https://storage.googleapis.com/";
		String url=defaultUrl+bucket+"/"+fileName;
		return url;
	}


	
}
