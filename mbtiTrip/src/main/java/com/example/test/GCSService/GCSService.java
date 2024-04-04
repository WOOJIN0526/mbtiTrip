package com.example.test.GCSService;

import org.springframework.web.multipart.MultipartFile;


public interface GCSService {

	public String uploadObject(MultipartFile file);

	public void deleteObject(String objectName);
	
	public String getFileName(String originalName);
	
	public String getUrl(String bucket,String fileName);
	
}
