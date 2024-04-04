package com.example.test.HashTag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.POST.DTO.PostDTO;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDao;
	
	@Autowired
	TagPostDAO tagpostDao;
	
	
	@Override
	public void createTagList(PostDTO post) {
		Pattern MY_PATTERN = Pattern.compile("#(\\S+)");
        Matcher mat = MY_PATTERN.matcher(post.getContent());
        List<String> tagList = new ArrayList<>();

        while(mat.find()) {
            tagList.add((mat.group(1)));
        }

        System.out.println("Create HashTags Success! -----> " + tagList);
        saveTag(tagList, post.getPostID());
		
	}

	@Override
	public boolean saveTag(List<String> tagList, Integer postId) {
		 	Integer result = 1;

	        for (String tag : tagList) {
	            Tag findResult = tagDao.findTagByContent(tag);

	            // 등록된 태그가 아니라면 태그부터 추가
	            if (findResult == null) {
	                tagDao.saveTag(tag);
	            }

	            // 태그-포스트 매핑 테이블에 데이터 추가
	            Tag findTag = tagDao.findTagByContent(tag);
	            tagDao.addTagCount(findTag.getTagId());
	            result = tagpostDao.saveTagPost(findTag.getTagId(), postId);
	        }

	        return result == 1;
	}

	@Override
	public boolean deleteTagPost(Integer postId) {
		 Integer result = tagpostDao.deleteTagPost(postId);
	     return result == 1;
	}

}
