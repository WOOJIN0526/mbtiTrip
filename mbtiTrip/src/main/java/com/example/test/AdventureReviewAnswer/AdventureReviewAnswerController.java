package com.example.test.AdventureReviewAnswer;

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


import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.Adventure.Service.Adventure_ReviewService;
import com.example.test.POST.AnswerForm;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;

import jakarta.validation.Valid;

@RequestMapping("/adventure/review/answer")
@Controller
public class AdventureReviewAnswerController {

	@Autowired
	AdventureReviewAnswerService adraService;
	
	@Autowired
	Adventure_ReviewService adrService;
	
	@Autowired
	UserService userservice;

	// 답변등록
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer answerID, 
            @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Adventure_ReviewDTO adrDto = this.adrService.getPost(answerID);
        UserDTO UserDto = this.userservice.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("adventure", adrDto);
            return "adventureReview_detail";
        }
        AdventureReviewAnswerDTO answerDto = this.adraService.create(adrDto, 
                answerForm.getContent(), UserDto);
        return String.format("redirect:/adventure/review/detail/%s#answer_%s", 
                answerDto.getAdventureReview().getAdventureID(), answerDto.getAnswerID());
    }
    
	//답변수정된것 가져옴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer answerID, Principal principal) {
       AdventureReviewAnswerDTO answerDto = this.adraService.getAnswer(answerID);
        if (!answerDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        answerForm.setContent(answerDto.getContent());
        return "answer_form";
    }
    
    //답변수정함
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String answerModify(@Valid AnswerForm answerForm, @PathVariable("id") Integer answerID,
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "answer_form";
        }
        AdventureReviewAnswerDTO answerDto = this.adraService.getAnswer(answerID);
        if (!answerDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adraService.modify(answerDto, answerForm.getContent());
        return String.format("redirect:/adventure/review/detail/%s#answer_%s", 
                answerDto.getAdventureReview().getUserId(), answerDto.getAnswerID());
    }
    
    //답변삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer answerID) {
        AdventureReviewAnswerDTO answerDto = this.adraService.getAnswer(answerID);
        if (!answerDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adraService.delete(answerDto);
        return String.format("redirect:/adventure/review/detail/%s", answerDto.getAdventureReview().getUserId());
    }
}
