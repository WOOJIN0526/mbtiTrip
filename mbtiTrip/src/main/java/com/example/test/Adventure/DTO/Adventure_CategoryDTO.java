package com.example.test.Adventure.DTO;



import java.util.List;

import com.example.test.POST.DTO.PostDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Adventure_CategoryDTO {

	
	private Integer adventureCategoryID;
	
	private String adventureCategory;
	
	private List<AdventureDTO> adList;
}
