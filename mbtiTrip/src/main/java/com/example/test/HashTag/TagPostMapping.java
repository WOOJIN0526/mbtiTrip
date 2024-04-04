package com.example.test.HashTag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TagPostMapping {

//  Integer mapId;
  Integer tagId;
  Integer postId;
  String content;

  public TagPostMapping(Integer tagId, Integer postId) {
//      this.mapId = mapId;
      this.tagId = tagId;
      this.postId = postId;
  }
}
