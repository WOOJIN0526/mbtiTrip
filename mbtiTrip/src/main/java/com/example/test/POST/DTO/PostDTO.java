package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class PostDTO {

	
	
	private Integer postID;
	
	private Integer postCategoryID;
	
	private Post_CategoryDTO post_category;
	
	private String userName;
	
	private String title;
	
	private String content;
	
	private Integer suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private List<AnswerDTO> answerList;
	
	private UserDTO writer; 
	
	private int views;
	
	private Integer itemID;
	

	
    @Override
    public String toString() {
        return "Post{" +
                "postCategoryID=" + postCategoryID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", itemID='" + itemID + '\'' +
                '}';
    }
}
