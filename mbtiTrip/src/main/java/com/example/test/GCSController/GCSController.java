package com.example.test.GCSController;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.GCSDTO.GCSDTO;
import com.example.test.GCSService.GCSService;

@Controller
public class GCSController {
	
	private GCSService gcsService;
	
	@PostMapping("/api/gcs/upload")
	@ResponseBody
	public ResponseEntity<String> objectUpload(GCSDTO dto){
		String url =gcsService.uploadObject(dto);
		 return ResponseEntity.ok(url);
	}
}
