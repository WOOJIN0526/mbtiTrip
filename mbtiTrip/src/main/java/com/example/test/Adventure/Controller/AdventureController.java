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

import com.example.test.Adventure.AdventureForm;
import com.example.test.Adventure.DTO.AdventureDTO;
import com.example.test.Adventure.Service.AdventureService;
import com.example.test.Adventure.Service.Adventure_CategoryService;


import jakarta.validation.Valid;

@Controller
public class AdventureController {

	@Autowired
	AdventureService adService;
	
	@Autowired
	Adventure_CategoryService adcService;
	
	@RequestMapping("/adventure/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "kw", defaultValue = "") String kw) {
        
        Page<AdventureDTO> paging = this.adService.getList(page, kw, "판매(ex 지역 or mbti)");
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AdventureForm adventureForm) {
        AdventureDTO post = this.adService.getPost(id);
        model.addAttribute("post", post);
        return "detail";
    }

    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String adCreate(AdventureForm adventureForm, Model model) {
    	model.addAttribute("categoryList", adcService.getList());
        return "form";
    }

    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String adCreate(@Valid AdventureForm adventureForm, 
            BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        //AdventureDTO admin = 관리자서비스(principal.getName());
        //this.postService.create(adventureForm.getTitle(), adventureForm.getContent(), 관리자Dto);
        return "redirect://list";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String adModify(AdventureForm adForm, @PathVariable("id") Integer id, Principal principal) {
        AdventureDTO adDto = this.adService.getPost(id);
        if(!adDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        adForm.setTitle(adDto.getAdventureName());
        adForm.setContent(adDto.getAdventureContent());
        return "form";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String adModify(@Valid AdventureForm adForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "form";
        }
        AdventureDTO adDto = this.adService.getPost(id);
        if (!adDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.adService.modify(adDto, adForm.getTitle(), adForm.getContent());
        return String.format("redirect://detail/%s", id);
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String adDelete(Principal principal, @PathVariable("id") Integer id) {
        AdventureDTO adDto = this.adService.getPost(id);
        if (!adDto.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.adService.delete(adDto);
        return "redirect:/";
    }
    
    //@PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String adVote(Principal principal, @PathVariable("id") Integer id) {
        //PostDTO questionDto = this.postService.getPost(id);
        //UserDTO siteUserDto = this.userService.findByUserName(principal.getName());
        //this.postService.vote(questionDto, siteUserDto);
        return String.format("redirect://detail/%s", id);
    }
    
  @GetMapping("/adventure_review/list")
  public String freepostList(Model model, @RequestParam(value="page", defaultValue="0") int page,
  	@RequestParam(value = "kw", defaultValue = "") String kw) {
      
      Page<AdventureDTO> paging = this.adService.getList(page, kw, "어드벤쳐리뷰");
      model.addAttribute("paging", paging);
      model.addAttribute("kw", kw);
      return "list";
  }
  
  
    
//    @GetMapping("/freepost/list")
//    public String freepostList(Model model, @RequestParam(value="page", defaultValue="0") int page,
//    	@RequestParam(value = "kw", defaultValue = "") String kw) {
//        
//        Page<AdventureDTO> paging = this.adService.getList(page, kw, "다른 카테고리");
//        model.addAttribute("paging", paging);
//        model.addAttribute("kw", kw);
//        return "list";
//    }
}