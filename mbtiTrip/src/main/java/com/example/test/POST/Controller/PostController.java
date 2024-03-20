package com.example.test.POST.Controller;

//import java.security.Principal;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.example.test.POST.DTO.AnswerForm;
//import com.example.test.POST.DTO.Post;
//import com.example.test.POST.DTO.PostDTO;
//import com.example.test.POST.DTO.PostForm;
//import com.example.test.POST.Service.AnswerService;
//import com.example.test.POST.Service.PostService;
//import com.example.test.User.DTO.UserDTO;
//import com.example.test.User.Service.UserService;
//
//import groovyjarjarpicocli.CommandLine.Model;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//
//@RequestMapping("/question")
//@RequiredArgsConstructor
//@Controller
//public class PostController {
//
//	@Autowired
//	PostService postService;
//
//	@Autowired
//	UserService userService;
//	
//	@RequestMapping("/list")
//	public String list(Model model,
//			@RequestParam(value="page", defaultValue = "0")int page,
//			@RequestParam(value="kw", defaultValue="")String kw) {
//		Page<Post> paging = this.postService.getList(page, kw);
//		model.addAttribute("paging" ,paging);
//		model.addAttribute("kw" ,kw);
//		return "post_list";
//	}
//
//    @RequestMapping(value = "/detail/{id}")
//    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
//        PostDTO post = this.postService.getPost(id);
//        model.addAttribute("post", post);
//        return "post_detail";
//    }
//
//    //@PreAuthorize("isAuthenticated()")
//    @GetMapping("/create")
//    public String questionCreate(PostForm PostForm) {
//        return "post_form";
//    }
//
//    //@PreAuthorize("isAuthenticated()")
//    @PostMapping("/create")
//    public String questionCreate(@Valid PostForm postForm, 
//            BindingResult bindingResult, Principal principal) {
//        if (bindingResult.hasErrors()) {
//            return "post_form";
//        }
//        UserDTO UserDto = this.userService.getUser(principal.getName());
//        this.postService.create(postForm.getTitle(), postForm.getContent(), UserDto);
//        return "redirect:/post/list";
//    }
//    
//    //@PreAuthorize("isAuthenticated()")
//    @GetMapping("/modify/{id}")
//    public String questionModify(PostForm postForm, @PathVariable("id") Integer id, Principal principal) {
//        PostDTO questionDto = this.postService.getPost(id);
//        if(!questionDto.getAuthor().getUserName().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        postForm.setTitle(questionDto.getTitle());
//        postForm.setContent(questionDto.getContent());
//        return "post_form";
//    }
//    
//    //@PreAuthorize("isAuthenticated()")
//    @PostMapping("/modify/{id}")
//    public String questionModify(@Valid PostForm postForm, BindingResult bindingResult, 
//            Principal principal, @PathVariable("id") Integer id) {
//        if (bindingResult.hasErrors()) {
//            return "post_form";
//        }
//        PostDTO questionDto = this.postService.getPost(id);
//        if (!questionDto.getAuthor().getUserName().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        this.postService.modify(questionDto, postForm.getTitle(), postForm.getContent());
//        return String.format("redirect:/post/detail/%s", id);
//    }
//    
//    //@PreAuthorize("isAuthenticated()")
//    @GetMapping("/delete/{id}")
//    public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
//        PostDTO questionDto = this.postService.getPost(id);
//        if (!questionDto.getAuthor().getUserName().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
//        this.postService.delete(questionDto);
//        return "redirect:/";
//    }
//    
//    //@PreAuthorize("isAuthenticated()")
//    @GetMapping("/vote/{id}")
//    public String questionVote(Principal principal, @PathVariable("id") Integer id) {
//        PostDTO questionDto = this.postService.getPost(id);
//        UserDTO siteUserDto = this.userService.getUser(principal.getName());
//        this.postService.vote(questionDto, siteUserDto);
//        return String.format("redirect:/post/detail/%s", id);
//    }
//	
//	
//}
