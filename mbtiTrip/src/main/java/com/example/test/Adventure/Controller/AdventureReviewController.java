package com.example.test.Adventure.Controller;

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


import com.example.test.Adventure.Adventure_ReviewForm;
import com.example.test.Adventure.DTO.Adventure_ReviewDTO;
import com.example.test.Adventure.Service.Adventure_CategoryService;
import com.example.test.Adventure.Service.Adventure_ReviewService;
import com.example.test.User.Service.UserService;

import jakarta.validation.Valid;

@Controller
public class AdventureReviewController {

	@Autowired
	Adventure_ReviewService adrService;
	
	@Autowired
	Adventure_CategoryService adcService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/adventureR/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<Adventure_ReviewDTO> paging = this.adrService.getList(page, kw, "판매(ex 지역 or mbti)");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }

    @RequestMapping(value = "/detailR/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, Adventure_ReviewForm adventureRForm) {
        Adventure_ReviewDTO post = this.adrService.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/createR")
    public String adCreate(Adventure_ReviewForm adventureRForm, Model model) {
    	model.addAttribute("categoryList", adcService.getList());
        return "form";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/createR")
    public String adCreate(@Valid Adventure_ReviewForm adventureRForm, 
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        //UserDTO admin = this.userService.getUser(principal.getName()); 관리자여야함
        //Adventure_CategoryDTO category = this.adcService.getCategory(adventureForm.getCategoryName());
        //this.adService.create(adventureForm.getTitle(), adventureForm.getContent(), UserDTO, category);
        return "redirect://list";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/modifyR/{id}")
    public String adModify(Adventure_ReviewForm adForm, @PathVariable("id") Integer id, Principal principal) {
        Adventure_ReviewDTO adrDto = this.adrService.getPost(id);
        if(!adrDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        adForm.setTitle(adrDto.getReviewTitle());
        adForm.setContent(adrDto.getContent());
        return "form";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/modifyR/{id}")
    public String adModify(@Valid Adventure_ReviewForm adRForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        Adventure_ReviewDTO adDto = this.adrService.getPost(id);
        if (!adDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adrService.modify(adDto, adRForm.getTitle(), adRForm.getContent());
        return String.format("redirect://detail/%s", id);
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/deleteR/{id}")
    public String adDelete(Principal principal, @PathVariable("id") Integer id) {
        Adventure_ReviewDTO adDto = this.adrService.getPost(id);
        if (!adDto.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adrService.delete(adDto);
        return "redirect:/";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/voteR/{id}")
    public String adVote(Principal principal, @PathVariable("id") Integer id) {
        //Adventure_ReviewDTO adrDto = this.adrService.getReview(id);
        //UserDTO siteUserDto = this.userService.findByUserName(principal.getName());
        //this.adrService.vote(adrDto, siteUserDto);
        return String.format("redirect://detail/%s", id);
    }
}
