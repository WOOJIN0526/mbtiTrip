package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO2 {

private Integer postID;
	
	private Integer postCategoryID;
	
	private Post_CategoryDTO post_category;
	
	private String userName;
	
	private String title;
	
	private String content;
	
	private Set<UserDTO> suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private List<AnswerDTO> answerList;
	
	private String writer; //UserDTO
	
	private int views;
	
	private MultipartFile[] file;	// 파일정보
	
	private String[] filePath;		// 파일경로

	
    @Override
    public String toString() {
        return "Post{" +
                "postCategoryID=" + postCategoryID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
