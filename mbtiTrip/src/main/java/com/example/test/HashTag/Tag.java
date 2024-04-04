package com.example.test.HashTag;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Tag {

	Integer tagId;
    String content;
    Integer tagCount;
    Integer weekCount;

    public Tag(Integer tagId, String content) {
        this.tagId = tagId;
        this.content = content;
    }
}