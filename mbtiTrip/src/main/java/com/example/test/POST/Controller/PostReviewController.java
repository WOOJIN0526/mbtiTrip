package com.example.test.POST.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.example.test.POST.AnswerForm;
import com.example.test.POST.PostForm;
import com.example.test.POST.DTO.PostReviewDTO;
import com.example.test.POST.DTO.Post_CategoryDTO;
import com.example.test.POST.Service.PostReviewService;
import com.example.test.POST.Service.Post_CategoryService;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.paging.Criteria;
import com.example.test.paging.PageDTO;

import jakarta.validation.Valid;

@RequestMapping("/post/review")
@Controller
public class PostReviewController {

	@Autowired
	PostReviewService prService;
	
	@Autowired
	Post_CategoryService pcService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		
		model.addAttribute("list", prService.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(prService.getTotal(cri), 10, cri));
		return "Review_main";
	}
	

	
    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer itemID, AnswerForm answerForm) {
        PostReviewDTO post = this.prService.getPost(itemID);
        model.addAttribute("post", post);
        return "review_detail";
    }

    //게시물을 작성할 때도 카테고리를 선택해서 게시물을 생성해야 한다. 
    //따라서 전체 카테고리 중에서 알맞는 카테고리를 선택할 수 있어야 한다. 즉, 아래와 같이 게시물 등록 폼에서 전체 카테고리를 조회한다.
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String postCreate(PostForm postForm, Model model) {
    	model.addAttribute("categoryList", pcService.getList());
        return "write_form";
    }

    //컨트롤러에 넘어온 카테고리 이름으로 카테고리 엔티티를 조회하고, 
    //조회한 카테고리 엔티티를 질문 엔티티에 넣어주고 저장하면 질문에 카테고리가 생기게 된다.
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(Model model, @Valid PostForm postForm, 
    	BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
        	model.addAttribute("categoryList", pcService.getList());
            return "write_form";
        }
        UserDTO User = this.userService.getUser(principal.getName());
        Post_CategoryDTO category = this.pcService.getCategory(postForm.getCategory());
        this.prService.create(postForm.getTitle(), postForm.getContent(), User, category);
        return "redirect:/post/review/list";
        }
    
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String postModify(PostForm postForm, @PathVariable("id") Integer itemID, Principal principal) {
        PostReviewDTO postDto = this.prService.getPost(itemID);
        if(!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setTitle(postDto.getTitle());
        postForm.setContent(postDto.getContent());
        return "write_form";
    }
    
    //수정
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String postModify(@Valid PostForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer itemID) {
        if (bindingResult.hasErrors()) {
            return "write_form";
        }
        PostReviewDTO postDto = this.prService.getPost(itemID);
        if (!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.prService.modify(postDto, postForm.getTitle(), postForm.getContent());
        return String.format("redirect:/post/review/detail/%s", itemID);
    }
    
    //삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer itemID) {
        PostReviewDTO postDto = this.prService.getPost(itemID);
        if (!postDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.prService.delete(postDto);
        return "redirect:/";
    }
    
    //추천
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/suggestion/{id}")
    public String postSuggestion(Principal principal, @PathVariable("id") Integer itemID) {
        PostReviewDTO postDto = this.prService.getPost(itemID);
        UserDTO UserDto = this.userService.getUser(principal.getName());
        this.prService.suggestion(postDto, UserDto);
        return String.format("redirect:/post/review/detail/%s", itemID);
    }
}
