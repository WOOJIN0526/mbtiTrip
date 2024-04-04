package com.example.test.HashTag;

import java.util.List;

import com.example.test.POST.DTO.PostDTO;

public interface TagService {
	 public void createTagList(PostDTO post);
	 public boolean saveTag(List<String> tagList, Integer postId);
	 public boolean deleteTagPost(Integer postId);
}
