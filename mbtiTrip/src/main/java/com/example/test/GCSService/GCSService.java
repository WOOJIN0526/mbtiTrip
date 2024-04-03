package com.example.test.GCSService;

import com.example.test.GCSDTO.GCSDTO;

public interface GCSService {

	public String uploadObject(GCSDTO dto);
	
	public String getFileName(String originalName);
	
	public String getUrl(String bucket,String fileName);
}
