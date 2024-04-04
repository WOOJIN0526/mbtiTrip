package com.example.test.POST.Service;



import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.HashTag.TagService;
import com.example.test.POST.DAO.PostDAO;

import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.paging.Criteria;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;






@Service
public  class PostServiceImpl implements PostService {

	@Autowired
	PostDAO postDAO;
	
	//@Autowired
	//TagService tagService;

	//해당게시글 가져옴, 조회수 증가
	@Override
	public PostDTO getPost(Integer userid) {
		 Optional<PostDTO> post = this.postDAO.findById(userid);
		  if (post.isPresent()) {
	        	PostDTO postDto = post.get();        	
	        	postDto.setViews(postDto.getViews()+1);        	
	        	this.postDAO.save(postDto);
	            	return postDto;
	        } else {
	            throw new DataNotFoundException("question not found");
	        }	
	}
	
	//생성
	@Override
	public PostDTO create(String title, String content, UserDTO user, Post_CategoryDTO category, MultipartFile file) {
		PostDTO postDto = new PostDTO();
        postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setPost_category(category);
        postDto.setUpdateDate(LocalDateTime.now());
        postDto.setWriter(user);
        
        // 파일 업로드 처리 시작
        String projectPath = System.getProperty("user.dir") // 프로젝트 경로를 가져옴
                + "\\src\\main\\resources\\static\\files"; // 파일이 저장될 폴더의 경로

        UUID uuid = UUID.randomUUID(); // 랜덤으로 식별자를 생성

        String fileName = uuid + "_" + file.getOriginalFilename(); // UUID와 파일이름을 포함된 파일 이름으로 저장

        File saveFile = new File(projectPath, fileName); // projectPath는 위에서 작성한 경로, name은 전달받을 이름

        try {
			file.transferTo(saveFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        postDto.setFilename(fileName);
        postDto.setFilepath("/files/" + fileName); // static 아래부분의 파일 경로로만으로도 접근이 가능
        // 파일 업로드 처리 끝
        
        // 생성된 post 객체에서 태그 리스트 생성하기
       // tagService.createTagList(postDto);
        
        return this.postDAO.save(postDto);
	}

	//수정
	@Override
	public PostDTO modify(PostDTO postDto, String title, String content) {
		postDto.setTitle(title);
        postDto.setContent(content);
        postDto.setModifyDate(LocalDateTime.now());
        
        
        return this.postDAO.save(postDto);
	}

	//삭제
	@Override
	public void delete(PostDTO postDto) {
		 this.postDAO.delete(postDto);
		
		//tagService.deleteTagPost(postDto);
	}

	//추천
	@Override
	public PostDTO suggestion(PostDTO postDto, UserDTO userDto) {
		postDto.getSuggestion().add(userDto);
        
        return this.postDAO.save(postDto);
	}

	// 페이징 처리된 게시물 목록을 반환	
	@Override
	public List<PostDTO> list(Criteria cri) throws Exception {

		return postDAO.list(cri);
	}

	// 페이지 수를 계산하기 위해 사용
	@Override
	public int listCount(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return postDAO.listCount(cri);
	}
	
	// 게시물을 조회하고 조회수 증가
		@Transactional
		public PostDTO detail(Integer postID, HttpServletRequest request, HttpServletResponse response) {
			 Cookie oldCookie = null;
			    Cookie[] cookies = request.getCookies();
			    if (cookies != null)
			        for (Cookie cookie : cookies)
			            if (cookie.getName().equals("postView"))
			                oldCookie = cookie;

			    if (oldCookie != null) {
			        if (!oldCookie.getValue().contains("[" + postID.toString() + "]")) {
			            postDAO.updateCount(postID);
			            oldCookie.setValue(oldCookie.getValue() + "_[" + postID + "]");
			            oldCookie.setPath("/");
			            oldCookie.setMaxAge(60 * 60 * 24);
			            response.addCookie(oldCookie);
			        }
			    }
			    else {
			        postDAO.updateCount(postID);
			        Cookie newCookie = new Cookie("postView","[" + postID + "]");
			        newCookie.setPath("/");
			        newCookie.setMaxAge(60 * 60 * 24);
			        response.addCookie(newCookie);
			    }

			    return postDAO.findById(postID).orElseThrow(() -> {
			        return new IllegalArgumentException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
			    });
			}

	
	





	
	
    

}
