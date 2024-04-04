package com.example.test.HashTag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagPostMapping {

//  Integer mapId;
  Integer tagId; //태그 식별자
  Integer postId; // 게시물 식별자
  String content; // 해당 태그 내용

  public TagPostMapping(Integer tagId, Integer postId) {
//      this.mapId = mapId;
      this.tagId = tagId;
      this.postId = postId;
  }
}
