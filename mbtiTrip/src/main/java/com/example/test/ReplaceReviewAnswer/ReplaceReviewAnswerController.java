package com.example.test.ReplaceReviewAnswer;

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

import com.example.test.POST.AnswerForm;
import com.example.test.User.DTO.UserDTO;
import com.example.test.User.Service.UserService;
import com.example.test.replace.DTO.ReplaceReviewDTO;
import com.example.test.replace.Service.ReplaceReviewService;

import jakarta.validation.Valid;

@RequestMapping("/replace/review/answer")
@Controller
public class ReplaceReviewAnswerController {

	@Autowired
	ReplaceReviewAnswerService rpraService;
	
	@Autowired
	ReplaceReviewService rprService;
	
	@Autowired
	UserService userservice;

	// 답변등록
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer answerID, 
            @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        ReplaceReviewDTO rprDto = this.rprService.getPost(answerID);
        UserDTO UserDto = this.userservice.getUser(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("replace", rprDto);
            return "replaceReview_detail";
        }
        ReplaceReviewAnswerDTO answerDto = this.rpraService.create(rprDto, 
                answerForm.getContent(), UserDto);
        return String.format("redirect:/replace/review/detail/%s#answer_%s", 
                answerDto.getReplaceReview().getReplaceID(), answerDto.getAnswerID());
    }
    
	//답변수정된것 가져옴
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String answerModify(AnswerForm answerForm, @PathVariable("id") Integer answerID, Principal principal) {
        ReplaceReviewAnswerDTO answerDto = this.rpraService.getAnswer(answerID);
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
        ReplaceReviewAnswerDTO answerDto = this.rpraService.getAnswer(answerID);
        if (!answerDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rpraService.modify(answerDto, answerForm.getContent());
        return String.format("redirect:/replace/review/detail/%s#answer_%s", 
                answerDto.getReplaceReview().getUserId(), answerDto.getAnswerID());
    }
    
    //답변삭제
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String answerDelete(Principal principal, @PathVariable("id") Integer answerID) {
        ReplaceReviewAnswerDTO answerDto = this.rpraService.getAnswer(answerID);
        if (!answerDto.getWriter().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rpraService.delete(answerDto);
        return String.format("redirect:/replace/review/detail/%s", answerDto.getReplaceReview().getUserId());
    }
}
