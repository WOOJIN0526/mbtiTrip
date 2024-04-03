package com.example.test.GCSDTO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GCSDTO {
	private String name;
	private MultipartFile file;
}
