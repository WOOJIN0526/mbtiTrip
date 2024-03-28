package com.example.test.POST.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import com.example.test.POST.DTO.AnswerDTO;
import com.example.test.POST.DTO.AnswerForm;
import com.example.test.POST.DTO.PostDTO;
import com.example.test.POST.Service.AnswerService;
import com.example.test.POST.Service.PostService;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@Controller
public class AnswerController {

		@Autowired
		AnswerService answerService;
		
		@Autowired
		PostService postservice;
		
		@Autowired
		UserService userservice;
	
		// 답변등록
		// @PreAuthorize("isAuthenticated()")
//	    @PostMapping("/create/{id}")
//	    public String createAnswer(Model model, @PathVariable("id") Integer id, 
//	            @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
//	        PostDTO postDto = this.postservice.getPost(id);
//	        UserDTO siteUserDto = this.userservice.getUser(principal.getName());
//	        if (bindingResult.hasErrors()) {
//	            model.addAttribute("post", postDto);
//	            return "question_detail";
//	        }
//	        AnswerDTO answerDto = this.answerService.create(postDto, 
//	                answerForm.getContent(), siteUserDto);
//	        return String.format("redirect:/post/detail/%s#answer_%s", 
//	                answerDto.getPost().getPostID(), answerDto.getAnswerID());
//	    }
		    
			//답변수정된것 가져옴
		    //@PreAuthorize("isAuthenticated()")
		    @GetMapping("/modify/{id}")
		    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
		        AnswerDTO answerDto = this.answerService.getAnswer(id);
		        if (!answerDto.getAuthor().getUsername().equals(principal.getName())) {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		        }
		        answerForm.setContent(answerDto.getContent());
		        return "form";
		    }
		    
		    //답변수정함
		    //@PreAuthorize("isAuthenticated()")
		    @PostMapping("/modify/{id}")
		    public String answerModify(@Valid AnswerForm answerForm, @PathVariable("id") Integer id,
		            BindingResult bindingResult, Principal principal) {
		        if (bindingResult.hasErrors()) {
		            return "form";
		        }
		        AnswerDTO answerDto = this.answerService.getAnswer(id);
		        if (!answerDto.getAuthor().getUsername().equals(principal.getName())) {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		        }
		        this.answerService.modify(answerDto, answerForm.getContent());
		        return String.format("redirect://detail/%s#answer_%s", 
		                answerDto.getPost().getUserId(), answerDto.getAnswerID());
		    }
		    
		    //답변삭제
		    //@PreAuthorize("isAuthenticated()")
		    @GetMapping("/delete/{id}")
		    public String answerDelete(Principal principal, @PathVariable("id") Integer id) {
		        AnswerDTO answerDto = this.answerService.getAnswer(id);
		        if (!answerDto.getAuthor().getUsername().equals(principal.getName())) {
		            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
		        }
		        this.answerService.delete(answerDto);
		        return String.format("redirect://detail/%s", answerDto.getPost().getUserId());
		    }
		    
		    //@PreAuthorize("isAuthenticated()")
//		    @GetMapping("/vote/{id}")
//		    public String answerVote(Principal principal, @PathVariable("id") Integer id) {
//		        AnswerDTO answerDto = this.answerService.getAnswer(id);
//		        UserDTO siteUserDto = this.userservice.getUser(principal.getName());
//		        this.answerService.vote(answerDto, siteUserDto);
//		        return String.format("redirect://detail/%s#answer_%s", 
//		                answerDto.getPost().getUserId(), answerDto.getAnswerID());
//		    }

}
