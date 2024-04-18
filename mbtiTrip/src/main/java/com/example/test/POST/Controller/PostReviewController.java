//package com.example.test.POST.Controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.example.test.POST.DTO.PostDTO;
//import com.example.test.POST.DTO.PostReviewDTO;
//import com.example.test.POST.Service.PostReviewService;
//import com.example.test.POST.Service.Post_CategoryService;
//import com.example.test.User.Service.UserService;
//import com.example.test.paging.Page;

//@Controller
//public class PostReviewController {
//
//	@Autowired
//	PostReviewService prService;
//	
//	@Autowired
//	Post_CategoryService pcService;
//	
//	@Autowired
//	UserService userService;
//	
//	//게시글 목록 화면
//	@RequestMapping(value = "/post/review/list", method = RequestMethod.GET)
//	public String list( Model model, Page page, @PathVariable("category") String category) throws Exception{
//	PostReviewDTO postReviewDTO = new PostReviewDTO();
//
//	  if(category.equals("review")) {
//		postReviewDTO.setPostCategoryID(1);
//	}
//		
//	Integer totalCount = null;
//	Integer rowPerPage = null;
//	Integer pageCount = null;
//	Integer pageNum = page.getPageNum();
//	String keyword = page.getKeyword();
//
//	// 조회된 전체 게시글 수
//	if( page.getTotalCount() == 0 )
//	totalCount =prService.getCount();
//			
//	else 
//	totalCount = page.getTotalCount();
//
//	//페이지 당 노출 게시글 수
//	if( page.getRowsPerPage() == 0 )
//	rowPerPage = 10;
//			
//	else 
//	rowPerPage = page.getRowsPerPage();
//
//	//노출 페이지수
//	if(page.getPageCount() == 0)
//	pageCount = 10;
//	else
//	pageCount = page.getPageCount();
//
//	if(page.getPageNum() == 0){
//	page = new Page(1, rowPerPage, totalCount, pageCount); 
//	} else{
//	page = new Page(pageNum, rowPerPage, totalCount, pageCount);
//	}
//			
//	if(keyword == null || keyword == ""){
//		page.setKeyword("");
//	List<PostReviewDTO> list = prService.getListPage(page);
//		model.addAttribute("list", list);
//	} else {
//		page.setKeyword(keyword);
//		model.addAttribute("list", prService.search(page));
//	}
//		  		
//				//model.addAttribute("list", postService.findPostByCategoryID(postDTO));
//				model.addAttribute("page", page);
//				model.addAttribute("type", category);
//				
//				return "notice_Board";
//
//		}

//		
//
//		
//		//게시글 읽기 화면
//		@RequestMapping(value = "/post/review/detail", method = RequestMethod.GET)
//		public String read(Model model, Integer itemId, Principal principal) throws Exception{
//
//			PostReviewDTO post = prService.getPost(itemId);
//				
//			String userName = "";
//			if( principal !=null ){
//				userName = principal.getName();
//				UserDTO user = userService.getUser(userName);
//					
//				model.addAttribute("userName", userName);
//			}
//
//			String writerName = post.getWriter();
//			if( writerName.equals(writerName)){
//				model.addAttribute("set", true); // 작성자일 경우만 수정, 삭제 노출
//			}
//
//
//			model.addAttribute("post", post);
//				
//
//			return "review_detail";
//		}
//
//		@PreAuthorize("isAuthenticated()")
//	    @RequestMapping(value = "/post/review/create", method = RequestMethod.GET)
//	    public String Create(Model model, PostReviewDTO post, Principal user) throws Exception{
//	    	
//	    	String userName = "";
//	    	if(user !=null){
//	    		userName = user.getName();
//	    		model.addAttribute("userName", userName);
//	    	}
//	    	model.addAttribute("categoryList", pcService.getList());
//	        return "write_form";
//	    }
//	    
//	    
//	    //@PostMapping("/noticeBoard/create")
//		@RequestMapping(value ="/post/review/creat", method = RequestMethod.POST)
//	    @ResponseBody
//	    public ResponseEntity<String> boardCreate(PostReviewDTO dto,Principal principal) throws Exception {
//	    	// DB에 연결할 후속작업 메서드 부탁드립니다.
//	    	System.out.println(dto.toString());
//	    	String userName =principal.getName();
//	    	dto.setWriter(userName);
//	    	this.prService.create(dto);
//	    	return ResponseEntity.ok("등록하였습니다.");
//	    	
//	    }
//
//	    
//	    @PreAuthorize("isAuthenticated()")
//	    @RequestMapping(value = "/post/review/modify", method = RequestMethod.GET)
//	    public String postModify(Model model, @PathVariable("id") Integer itemID, Principal user) throws Exception {
//	    	String userName = "";
//	    	if( user != null) {
//	    		userName = user.getName();
//	    		model.addAttribute("userName", userName);
//	    	}
//	     	PostReviewDTO post = prService.getPost(itemID);
//	    	String writer = post.getWriter();
//	    	if( !writer.equals(userName)){
//	    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//	    	}
//	    	model.addAttribute("post", post);
//	    	
//	    	return "write_form";
//	 
//	    }
//	    
//	    //수정
//	    @PreAuthorize("isAuthenticated()")
//	    @PostMapping("/post/review/modify/")
//	    public String postModify(Model model, BindingResult bindingResult, Principal principal, @PathVariable("id") PostReviewDTO post) throws Exception {
//	        if (bindingResult.hasErrors()) {
//	            return "write_form";
//	        }
//	        
//	        if (!post.getWriter().equals(principal.getName())) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//	        }
//
//	        this.prService.modify(post);
//	        return String.format("redirect:/post/review/detail/%s", post);
//	    }
//	    
//	    //삭제
//	    @PreAuthorize("isAuthenticated()")
//	    @RequestMapping(value = "/post/review/remove", method = RequestMethod.POST)
//	    public String postDelete(Principal principal, @PathVariable("id") Integer itemID) throws Exception {
//	        PostReviewDTO postDto = this.prService.getPost(itemID);
//	        if (!postDto.getWriter().equals(principal.getName())) {
//	            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//	        }
//	        this.prService.remove(itemID);
//	        return "redirect:/";
//	    }
//	    
//	    //추천
//	    @PreAuthorize("isAuthenticated()")
//	    @GetMapping("/post/review/suggestion/{id}")
//	    public String Suggestion(Principal principal, @PathVariable("id") Integer postID) throws Exception {
//	        PostReviewDTO postDto = this.prService.getPost(postID);
//	        UserDTO UserDto = this.userService.getUser(principal.getName());
//	        this.prService.suggestion(postDto, UserDto);
//	        return String.format("redirect:/post/detail/%s", postID);
//	    }
//	    
//	    //댓글 등록
//	    @RequestMapping(value = "/post/review/replyRegister", method = RequestMethod.GET)
//	    public String replyRegister(Model model, AnswerDTO reply, Principal user) throws Exception{
//
//	    	String userName = "";
//	    	if( user !=null){
//	    		userName = user.getName();
//	    		model.addAttribute("userName", userName);
//	    	} 
//
//	    	//댓글 등록
//	    	prService.replyRegister(reply);
//
//	    	PostReviewDTO postId = reply.getPrID();
//
//	    	//댓글 목록 조회
//	    	model.addAttribute("replyList", prService.replyList(postId));
//	    	
//	    	return "review_detail";
//	    }
//
//	    //댓글 수정
//	    @RequestMapping(value = "/post/review/replyModify", method = RequestMethod.GET)
//	    public String replyModify(Model model, AnswerDTO reply, Principal user) throws Exception{
//
//	    	String userName = "";
//	    	if( user !=null) {
//	    		userName = user.getName();
//	    		model.addAttribute("userName", userName);	
//	    	}
//
//	    	//댓글 수정
//	    	prService.replyModify(reply);
//
//	    	PostReviewDTO postId = reply.getPrID();
//	    	
//	    	//댓글 목록 조회
//	    	model.addAttribute("replyList", prService.replyList(postId));
//
//	    	return "review_detail";
//	    }
//
//	    //댓글 삭제
//	    @RequestMapping(value = "/post/review/replyRemove", method = RequestMethod.GET)
//	    public String replyRemove(Model model, AnswerDTO reply, Principal user) throws Exception{
//
//	    	String userName = "";
//	    	if( user != null) {
//	    		userName = user.getName();
//	    		model.addAttribute("userName", userName);
//	    	}
//
//	    	//댓글 삭제
//	    	prService.replyRemove(reply.getAnswerID());
//
//	    	PostReviewDTO postId = reply.getPrID();
//
//	    	//댓글 목록 조회
//	    	model.addAttribute("replyList", prService.replyList(postId));
//
//	    	return "review_detail";
//	    }
//}
