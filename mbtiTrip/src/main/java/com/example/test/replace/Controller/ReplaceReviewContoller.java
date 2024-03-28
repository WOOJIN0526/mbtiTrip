package com.example.test.replace.Controller;

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


import com.example.test.User.Service.UserService;
import com.example.test.replace.ReplaceReviewForm;
import com.example.test.replace.DTO.ReplaceReviewDTO;
import com.example.test.replace.Service.ReplaceCategoryService;
import com.example.test.replace.Service.ReplaceReviewService;

import jakarta.validation.Valid;

@Controller
public class ReplaceReviewContoller {

	@Autowired
	ReplaceReviewService rprService;
	
	@Autowired
	ReplaceCategoryService rpcService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/replaceR/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<ReplaceReviewDTO> paging = this.rprService.getList(page, kw, "숙소리뷰");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }

    @RequestMapping(value = "/detailrer/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, ReplaceReviewForm replaceRForm) {
        ReplaceReviewDTO post = this.rprService.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/createRer")
    public String rprCreate(ReplaceReviewForm replaceRForm, Model model) {
    	model.addAttribute("categoryList", rpcService.getList());
        return "form";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/createRer")
    public String rprCreate(@Valid ReplaceReviewForm replaceRForm, 
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        //UserDTO admin = this.userService.getUser(principal.getName()); 관리자여야함
        //ReplaceCategoryDTO category = this.rprService.getCategory(adventureForm.getCategoryName());
        //this.rprService.create(adventureForm.getTitle(), adventureForm.getContent(), UserDTO, category);
        return "redirect://list";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/modifyRer/{id}")
    public String rprModify(ReplaceReviewForm rprForm, @PathVariable("id") Integer id, Principal principal) {
        ReplaceReviewDTO rprDto = this.rprService.getPost(id);
        if(!rprDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        rprForm.setTitle(rprDto.getReviewTitle());
        rprForm.setContent(rprDto.getContent());
        return "form";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/modifyRer/{id}")
    public String rprModify(@Valid ReplaceReviewForm rprForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        ReplaceReviewDTO rprDto = this.rprService.getPost(id);
        if (!rprDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.rprService.modify(rprDto, rprForm.getTitle(), rprForm.getContent());
        return String.format("redirect://detail/%s", id);
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/deleteRer/{id}")
    public String rprDelete(Principal principal, @PathVariable("id") Integer id) {
        ReplaceReviewDTO adDto = this.rprService.getPost(id);
        if (!adDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.rprService.delete(adDto);
        return "redirect:/";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/voteRer/{id}")
    public String rprVote(Principal principal, @PathVariable("id") Integer id) {
        //ReplaceReviewDTO rprDto = this.rprService.getReview(id);
        //UserDTO siteUserDto = this.userService.findByUserName(principal.getName());
        //this.rprService.vote(rprDto, siteUserDto);
        return String.format("redirect://detail/%s", id);
    }

}
