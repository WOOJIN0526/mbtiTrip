package com.example.test.POST.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.example.test.User.DTO.UserDTO;

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
	
	private Set<UserDTO> suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	private List<AnswerDTO> answerList;
	
	
	private UserDTO writer;
	
	//조회수
	private int views;

	private String filename; // 파일 이름
    private String filepath; // 파일이 저장된 경로
    @Override
    public String toString() {
        return "Post{" +
                "postCategoryID=" + postCategoryID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
