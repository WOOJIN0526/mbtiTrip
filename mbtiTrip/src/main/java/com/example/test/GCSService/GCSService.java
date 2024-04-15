package com.example.test.GCSService;



import org.springframework.web.multipart.MultipartFile;

import com.example.testExcepion.GCSS.GCSSException;


public interface GCSService {

	public String uploadObject(MultipartFile file) throws GCSSException;

	public void deleteObject(String currentURL);
	
	public String getFileName(String originalName);
	
	public String getUrl(String bucket,String fileName);
	
	
	
}
