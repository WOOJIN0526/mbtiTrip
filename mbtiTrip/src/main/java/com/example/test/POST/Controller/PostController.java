package com.example.test.POST.Controller;



import java.security.Principal;
import java.time.LocalDateTime;
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
import com.example.test.POST.Service.PostReviewService;
import com.example.test.POST.Service.PostService;
import com.example.test.POST.Service.Post_CategoryService;
import com.example.test.User.DAO.UserDAO;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserHistoryService;
import com.example.test.User.Service.UserService;
import com.example.test.item.DTO.ItemDTO;
import com.example.test.paging.Criteria;
import com.example.test.paging.Page;
import com.example.test.paging.PageDTO;
import com.example.test.paging.PaginationVo;

import jakarta.validation.Valid;






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

	@Autowired
	UserDAO userDao;
	
	//게시글 목록 화면
		@RequestMapping(value = "/post/{category}/list", method = RequestMethod.GET)
		public String list( Model model, Page page, @PathVariable("category") String category) throws Exception{
		  
		  	Integer totalCount = null;
			Integer rowPerPage = null;
			Integer pageCount = null;
			Integer pageNum = page.getPageNum();
			String keyword = page.getKeyword();

			// 조회된 전체 게시글 수
			if( page.getTotalCount() == 0 )
				totalCount =postService.totalCount();
			
			 else 
			    totalCount = page.getTotalCount();

			//페이지 당 노출 게시글 수
			if( page.getRowsPerPage() == 0 )
				rowPerPage = 10;
			
			else 
				rowPerPage = page.getRowsPerPage();

			//노출 페이지수
			if(page.getPageCount() == 0)
				pageCount = 10;
			else
				pageCount = page.getPageCount();

			if(page.getPageNum() == 0){
				page = new Page(1, rowPerPage, totalCount, pageCount); 
			} else{
				page = new Page(pageNum, rowPerPage, totalCount, pageCount);
			}
			if(category.equals("noticeBoard")) {
			        page.setPostCateGoryID(2);
			        
			  } else if(category.equals("review")) {
			        page.setPostCateGoryID(1);
			  }
			if(keyword == null || keyword == ""){
				page.setKeyword("");
				List<PostDTO> list = postService.getListPage(page);
				model.addAttribute("list", list);
			} else {
				page.setKeyword(keyword);
				model.addAttribute("list", postService.search(page));
			}
		  		
				//model.addAttribute("list", postService.findPostByCategoryID(postDTO));
				model.addAttribute("page", page);
				model.addAttribute("type", category);
				
				return "notice_Board";

		}
	

	
	//게시글 읽기 화면
	@RequestMapping(value = "/post/{category}/detail/{postID}", method = RequestMethod.GET)
	public String read(Model model,@PathVariable("postID") Integer postID, Principal principal, @PathVariable("category") String category) throws Exception{

		PostDTO post = postService.getPost(postID,principal);
		if(category.equals("review")) {
			Integer itemID = post.getItemID();
			ItemDTO item = postService.getItem(itemID);
			model.addAttribute("item",item);
		}
		String userName =post.getUserName();
		UserDTO user = userService.getUserByUserName(post.getUserName());
		model.addAttribute("user", user);
		model.addAttribute("post", post);
		

		return "post_detail";
	}

	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "/post/{category}/create", method = RequestMethod.GET)
    public String Create(Model model, PostDTO post, Principal user, @PathVariable("category") String category) throws Exception{
		PostDTO postDTO = new PostDTO();
		int value=0;
	      if(category.equals("noticeBoard")) {
	            value =2;
	      } else if(category.contains("review")) {
	            value =1;
	            int itemID = Integer.parseInt(category.replaceAll("[^0-9]", ""));
	            model.addAttribute("itemID",itemID);
	      }
    	model.addAttribute("type",value);
    	
        return "write_form";
    }
    
    
    //@PostMapping("/noticeBoard/create")
	@RequestMapping(value ="/post/{category}/create", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> boardCreate(PostDTO dto,Principal principal, @PathVariable("category") String category) throws Exception {
    	// DB에 연결할 후속작업 메서드 부탁드립니다.
		
    	String userName =userDao.getUserNameByuserID(principal.getName());
    	dto.setUserName(userName);
    	dto.setUpdateDate(LocalDateTime.now());
    	
    	int result =postService.create(dto);
    	if(result==1) {
    		return ResponseEntity.ok("등록하였습니다.");
    	}else {
    		return ResponseEntity.ok("등록에 실패했습니다.");
    	}
    	
    	
    }

    
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "/post/noticeBoard/modify", method = RequestMethod.GET)
    public String postModify(Model model, @PathVariable("id") Integer postID, Principal user) throws Exception {
    	String userName = "";
    	if( user != null) {
    		userName = user.getName();
    		model.addAttribute("userName", userName);
    	}
     	PostDTO post = postService.getPost(postID, user);
    	UserDTO writer = post.getWriter();
    	if( !writer.equals(userName)){
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
    	}
    	model.addAttribute("post", post);
    	
    	return "write_form";
 
    }
    
    //수정
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @PostMapping("/post/noticeBoard/modify/")
    public String postModify(Model model, BindingResult bindingResult, Principal principal, @PathVariable("id") PostDTO post) throws Exception {
        if (bindingResult.hasErrors()) {
            return "write_form";
        }
        
        if (!post.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        this.postService.modify(post);
        return String.format("redirect:/post/noticeBoard/detail/%s", post);
    }
    
    //삭제
	@PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "/post/noticeBoard/remove", method = RequestMethod.POST)
    public String postDelete(Principal principal, @PathVariable("id") Integer postID) throws Exception {
        PostDTO postDto = this.postService.getPost(postID, principal);
        if (!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.postService.remove(postID);
        return "redirect:/";
    }
    
//    //추천
//    @PreAuthorize("isAuthenticated()")
//    @GetMapping("/suggestion/{id}")
//    public String Suggestion(Principal principal, @PathVariable("id") Integer postID) throws Exception {
//        PostDTO postDto = this.postService.getPost(postID);
//        UserDTO UserDto = this.userService.getUser(principal.getName());
//        this.postService.suggestion(postDto, UserDto);
//        return String.format("redirect:/post/detail/%s", postID);
//    }
    
    //댓글 등록
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "/post/noticeBoard/replyRegister", method = RequestMethod.GET)
    public String replyRegister(Model model, AnswerDTO reply, Principal user) throws Exception{

    	String userName = "";
    	if( user !=null){
    		userName = user.getName();
    		model.addAttribute("userName", userName);
    	} 

    	//댓글 등록
    	postService.replyRegister(reply);

    	PostDTO postId = reply.getPostID();

    	//댓글 목록 조회
    	model.addAttribute("replyList", postService.replyList(postId));
    	
    	return "post_detail";
    }

    //댓글 수정
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "replyModify", method = RequestMethod.GET)
    public String replyModify(Model model, AnswerDTO reply, Principal user) throws Exception{

    	String userName = "";
    	if( user !=null) {
    		userName = user.getName();
    		model.addAttribute("userName", userName);	
    	}

    	//댓글 수정
    	postService.replyModify(reply);

    	PostDTO postId = reply.getPostID();
    	
    	//댓글 목록 조회
    	model.addAttribute("replyList", postService.replyList(postId));

    	return "post_list";
    }

    //댓글 삭제
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER')")
    @RequestMapping(value = "replyRemove", method = RequestMethod.GET)
    public String replyRemove(Model model, AnswerDTO reply, Principal user) throws Exception{

    	String userName = "";
    	if( user != null) {
    		userName = user.getName();
    		model.addAttribute("userName", userName);
    	}

    	//댓글 삭제
    	postService.replyRemove(reply.getAnswerID());

    	PostDTO postId = reply.getPostID();

    	//댓글 목록 조회
    	model.addAttribute("replyList", postService.replyList(postId));

    	return "post_list";
    }
    

    
   

    



   


	
 
    
}
