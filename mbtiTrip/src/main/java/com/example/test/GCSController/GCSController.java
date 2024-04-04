package com.example.test.GCSController;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.GCSDTO.GCSDTO;
import com.example.test.GCSService.GCSService;

@Controller
public class GCSController {
	@Autowired
	private GCSService gcsService;
	
	@PostMapping("/api/gcs/upload")
	@ResponseBody
	public ResponseEntity<String> objectUpload(GCSDTO dto){
		try {
			System.out.println(dto.getFile().getBytes().toString());
			String url =gcsService.uploadObject(dto);
			System.out.println(url);
			return ResponseEntity.ok(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
		 
	}
}
