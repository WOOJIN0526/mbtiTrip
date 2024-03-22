package com.example.test.POST.DTO;



import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post_CategoryDTO {

	
	private Integer postCategoryID;
	
	private String postCategory;
	
	private List<PostDTO> postList;
}
