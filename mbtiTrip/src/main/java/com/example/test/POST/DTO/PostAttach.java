package com.example.test.POST.DTO;

import java.util.Date;

import lombok.Data;

@Data
public class PostAttach {

	private int fileNo;
	private String fullName;
	private String fileName;
	private int boardNo;
	private Date regDate;
}
