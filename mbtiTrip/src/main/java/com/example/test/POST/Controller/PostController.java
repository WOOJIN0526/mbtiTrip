package com.example.test.POST.Controller;



import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;


import com.example.test.POST.AnswerForm;
import com.example.test.POST.PostForm;
import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.PostDTO;

import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.POST.Service.PostService;
import com.example.test.POST.Service.Post_CategoryService;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;

import org.springframework.data.domain.Page;
import jakarta.validation.Valid;





@RequestMapping("/post")
@Controller
public class PostController {

	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	Post_CategoryService postCategoryService;
	
	@Autowired
	UserHistoryService userHistoryService;
	
	//게시물 목록
	@GetMapping("/noticeBoard/list")
	public String list(Criteria cri, Model model) {
	
	model.addAttribute("list", postService.getList(cri));//게시글 목록 가져오기
	model.addAttribute("pageMaker", new PageDTO(postService.getTotal(cri), 10, cri));//페이지당 게시물 10개
	return "notice_board";
	}
	

	
	//디테일
    @RequestMapping(value = "/noticeBoard/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer postID, Principal user) {
        PostDTO post = this.postService.getPost(postID);
        model.addAttribute("post", post);
        return "detail";
    }

    
    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/noticeBoard/create")
    public String postCreate(PostForm postForm, Model model) {
    	model.addAttribute("categoryList", postCategoryService.getList());
        return "write_form";
    }

    @PostMapping("/noticeBoard/create")
    @ResponseBody
    public ResponseEntity<String> boardCreate(PostDTO dto,Principal principal) {
    	// DB에 연결할 후속작업 메서드 부탁드립니다.
    	System.out.println(dto.toString());
    	String userName =principal.getName();
    	dto.setUserName(userName);
    	postService.create(dto);
    	return ResponseEntity.ok("등록하였습니다.");
    	
    }

    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String postModify(Model model, @PathVariable("id") Integer postID, Principal user) {
    	String userName = "";
    	if( user != null) {
    		userName = user.getName();
    		model.addAttribute("userName", userName);
    	}
     	PostDTO post = postService.getPost(postID);
    	UserDTO writer = post.getWriter();
    	
    	model.addAttribute("post", post);
    	
    	return "write_form";
 
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String postModify( BindingResult bindingResult, Principal principal, @PathVariable("id") Integer postID) {
        if (bindingResult.hasErrors()) {
            return "write_form";
        }
        PostDTO postDto = this.postService.getPost(postID);
        if (!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        postService.modify(postDto);
        return String.format("redirect:/post/noticeBoard/detail/%s", postID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer postID) {
        PostDTO postDto = this.postService.getPost(postID);
        if (!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.postService.delete(postDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String postSuggestion(Principal principal, @PathVariable("id") Integer postID) {
        PostDTO postDto = this.postService.getPost(postID);
        UserDTO UserDto = this.userService.getUser(principal.getName());
        this.postService.suggestion(postDto, UserDto);
        return String.format("redirect:/post/detail/%s", postID);
    }
    

    

    
   
//    @GetMapping("/noticeBoard/list")//이거그냥 url무시하시고 postlist 보여주는거에 리턴만 이걸로 맞춰주세요
//    public String board(Model model) {
//    	//게시글 불러와서 모델에 넣어주세요 list<PostDTO>로 postCategoryID=2
//    	// postCategoryID=2인 게시글 목록 조회
//        //List<PostDTO> postDTOList = postService.findPostByCategoryID(2L);
//
//        // 모델에 게시글 목록 추가
//        //model.addAttribute("postList", postDTOList);
//    	return"notice_board";
//    }
    

//    @GetMapping("/noticeBoard/create")//이거그냥 url무시하시고 postcreate 보여주는거에 리턴만 이걸로 맞춰주세요
//   public String boardCreate() {
//   	// DB에 연결할 후속작업 메서드 부탁드립니다.
//   //	model.addAttribute("categoryList", postCategoryService.getList());
//   	return"write_form";
//   }

   


	
 
    
}
