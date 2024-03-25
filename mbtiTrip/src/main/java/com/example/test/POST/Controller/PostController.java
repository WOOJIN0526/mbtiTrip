package com.example.test.POST.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.POST.DTO.AnswerForm;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.DTO.PostForm;
import com.example.test.POST.Service.PostService;
import com.example.test.POST.Service.Post_CategoryService;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

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
	
	@RequestMapping("/question/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<PostDTO> paging = this.postService.getList(page, kw, "질문");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        PostDTO post = this.postService.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String postCreate(PostForm postForm, Model model) {
    	model.addAttribute("categoryList", postCategoryService.getList());
        return "form";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid PostForm postForm, 
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        //UserDTO userDto = this.userService.getUser(principal.getName());
        //this.postService.create(postForm.getTitle(), postForm.getContent(), userDto);
        return "redirect://list";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String postModify(PostForm postForm, @PathVariable("id") Integer id, Principal principal) {
        PostDTO postDto = this.postService.getPost(id);
        if(!postDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        postForm.setTitle(postDto.getTitle());
        postForm.setContent(postDto.getContent());
        return "form";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String postModify(@Valid PostForm postForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        PostDTO postDto = this.postService.getPost(id);
        if (!postDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.postService.modify(postDto, postForm.getTitle(), postForm.getContent());
        return String.format("redirect://detail/%s", id);
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String postDelete(Principal principal, @PathVariable("id") Integer id) {
        PostDTO postDto = this.postService.getPost(id);
        if (!postDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.postService.delete(postDto);
        return "redirect:/";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String postVote(Principal principal, @PathVariable("id") Integer id) {
        //PostDTO questionDto = this.postService.getPost(id);
        //UserDTO siteUserDto = this.userService.getUser(principal.getName());
        //this.postService.vote(questionDto, siteUserDto);
        return String.format("redirect://detail/%s", id);
    }
    
    @GetMapping("/freepost/list")
    public String freepostList(Model model, @RequestParam(value="page", defaultValue="0") int page,
    	@RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<PostDTO> paging = this.postService.getList(page, kw, "자유");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }
    
    @GetMapping("/support")
    public String support() {
    	return "customer_center";
    }
    
}
