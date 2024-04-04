package com.example.test.replace.DTO;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.example.test.User.DTO.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplaceDTO {


	private Integer replaceID;    //pk
	
	private String replaceCategoryID;   //fk
	
	private Integer mbtiID;    //fk

	private Integer cityID;    //fk
	
	private Integer postCategoryID;    //fk
	
	private Integer replaceType;  //fk
	
	private Integer userid;
	 
	private String businessName;
	
	private String replaceName;
	
	private String replacePostCode;
	
	private String replaceLocation;
	
	private String locationdetail;
	
	private Integer replacePrice;

	private String replaceContents;
	
	private String tel;
	
	private String replaceAdmin;
	
	private MultipartFile[] file;
	
	private Integer R_rating_avg;
	
	private Set<UserDTO> suggestion;
	
	private LocalDateTime updateDate;
	
	private LocalDateTime modifyDate;
	
	//조회수
	private int views;

 
    public void addViewCount() {
        this.views++;
    }
    @Override
    public String toString() {
        return "ReplaceDTO{" +
                "replaceType=" + replaceType +
                ", businessName='" + businessName + '\'' +
                ", replaceName='" + replaceName + '\'' +
                ", replacePostCode='" + replacePostCode + '\'' +
                ", replaceLocation='" + replaceLocation + '\'' +
                ", locationdetail='" + locationdetail + '\'' +
                ", replacePrice=" + replacePrice +
                ", replaceContents='" + replaceContents + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }

}
