package com.example.test.POST;

import java.time.LocalDateTime;


import lombok.Data;

@Data
public class PostAttach {

	private int fileNo;
	private String fullName;
	private String fileName;
	private Integer postId;
	private LocalDateTime updateDate;
}
