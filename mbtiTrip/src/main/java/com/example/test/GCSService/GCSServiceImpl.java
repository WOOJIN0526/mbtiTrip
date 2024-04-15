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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.GCSDTO.GCSDTO;
import com.example.testExcepion.GCSS.GCSSException;
import com.example.testExcepion.GCSS.GCSSExceptionEnum;
import com.example.testExcepion.Item.ItemException;
import com.example.testExcepion.Item.ItemExceptionEnum;
import com.example.testExcepion.Utile.NotSupportMediaTypeException;
import com.example.testExcepion.Utile.UtileExceptionCode;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import jakarta.transaction.NotSupportedException;

@Service
public class GCSServiceImpl implements GCSService{
	
	@Value("${spring.cloud.gcp.storage.bucket}")
	private String bucketName;
	@Value("${spring.cloud.gcp.storage.credentials.location}")
	private String classPath;
	@Value("${spring.cloud.gcp.storage.project-id}")
	private String projectId;
	
	/*허용하는 파일 업로드 확장자 명 정의 */
	private String[] mediaType = {"jpeg", "jpg", "png", "gif"};
	
	@Override
	/**
	 * 주어진 MultipartFile 객체를 사용하여 파일을 버킷에 업로드합니다.
	 * @param {MultipartFile} file 업로드할 파일(이미지)을 나타내는 MultipartFile 객체입니다.
	 * @return {String} url 업로드된 파일의 URL입니다.
	 */
	public String uploadObject(MultipartFile file)  {
		// try-with-resources를 통해 자동으로 close메소드를 호출
		exeCk(file);
	    try (InputStream keyFile = ResourceUtils.getURL(classPath).openStream()) {
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
	    	throw new GCSSException(GCSSExceptionEnum.GCSS_FILE_NOTFOUND);
	    } catch (IOException e) {
	    	throw new GCSSException(GCSSExceptionEnum.GCSS_INVALID_PARAMETERS);
	    } catch (Exception e) {
	    	throw new ItemException(ItemExceptionEnum.ITEM_UNKNOWN_ERROR);
	    }
	}

	
	/*
	 * @Param file = MultipartFile 타입의 파라미터 
	 * StringUtils의 내장 메소드를 사용해, 확장자 추출
	 * 미리 선언된 배열과 일치하는 지 확인 
	 * 동일하지 않다면 exception throw
	 * */
	private void exeCk(MultipartFile file) {
		boolean ck = true;
		String exe = StringUtils.getFilenameExtension(file.getOriginalFilename()); 
		for(String key : mediaType) {
			if(key.equals(exe)) {
				ck = false;
			}
		}
		if(ck) {
			throw new NotSupportMediaTypeException(UtileExceptionCode.UNSUPPORTED_MEDIA_TYPE);
		}
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
		String defaultUrl ="https://storage.googleapis.com/kdt3th_project/";
		int dLen =defaultUrl.length();
		String objectName =currentURL.substring(dLen);
		//스토리지에 권한부여 및 삭제작업 입니다.
		try(InputStream keyFile = ResourceUtils.getURL(classPath).openStream()){
			Storage storage = StorageOptions.newBuilder()
	                .setCredentials(GoogleCredentials.fromStream(keyFile))
	                .build()
	                .getService();
			Blob blob = storage.get(bucketName, objectName);
		    if (blob == null) {
		      return;
		    }
		    Storage.BlobSourceOption precondition =
		        Storage.BlobSourceOption.generationMatch(blob.getGeneration());
	
		    storage.delete(bucketName, objectName, precondition);
	
		    System.out.println("Object " + objectName + " was deleted from " + bucketName);
		    
			} catch (FileNotFoundException e) {
				throw new GCSSException(GCSSExceptionEnum.GCSS_FILE_NOTFOUND);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new GCSSException(GCSSExceptionEnum.GCSS_PERMISSION_DENIED);
			}
		 
		
	}


	
}
