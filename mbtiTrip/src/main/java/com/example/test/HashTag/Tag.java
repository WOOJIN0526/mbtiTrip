package com.example.test.HashTag;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Tag {

	Integer tagId; //태그 아이디
    String content; // 태그 내용
    
    

    public Tag(Integer tagId, String content) {
        this.tagId = tagId;
        this.content = content;
    }
}