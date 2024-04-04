package com.example.test.HashTag;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.test.POST.DTO.PostDTO;

//@Service
public class TagServiceImpl implements TagService {

	@Autowired
	TagDAO tagDao;
	
	@Autowired
	TagPostDAO tagpostDao;
	
	
	@Override	// PostDTO를 매개변수로 받아 해당 게시물에 대한 태그 리스트를 생성
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


	@Override //태그 리스트와 게시물의 ID를 받아서 해당 게시물에 대한 태그들을 저장하는 메서드
	public Integer saveTag(List<String> tagList, Integer postId) {
		 Integer result = 1;

	        for (String tag : tagList) {
	            Tag findResult = tagDao.findTagByContent(tag);

	            // 등록된 태그가 아니라면 태그부터 추가
	            if (findResult == null) {
	                tagDao.saveTag(tag);
	            }

	            // 태그-포스트 매핑 테이블에 데이터 추가
	            Tag findTag = tagDao.findTagByContent(tag);
	            
	            result = tagpostDao.saveTagPost(findTag.getTagId(), postId);
	        }

	        return result;
	    
	}


	@Override //게시물에 대한 태그를 삭제하는 메서드
	public void deleteTagPost(PostDTO postDto) {
		this.tagpostDao.deleteTagPost(postDto);
        
	}


//	@Override
//	public List<Tag> findByTagCount() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	

}
