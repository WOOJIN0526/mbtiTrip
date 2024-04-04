package com.example.test.HashTag;

import java.util.List;

import com.example.test.POST.DTO.PostDTO;

public interface TagService {
	 public void createTagList(PostDTO post);
	 public Integer saveTag(List<String> tagList, Integer postId);
	 public void deleteTagPost(PostDTO postDto);
	 //public List<Tag> findByTagCount();
}
